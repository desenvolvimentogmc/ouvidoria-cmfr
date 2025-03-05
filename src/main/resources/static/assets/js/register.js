/**
 * @see handleSubmit - monta o formulário e envia usando fetch
 */

const form = document.getElementById("form-register");
const btnAnterior = document.getElementById("btnAnterior");
const btnProximo = document.getElementById("btnProximo");
const btnSalvar = document.getElementById("btnSalvar");

form.addEventListener("submit", (event) => {
  event.preventDefault();

  btnSalvar.disabled = true;
  btnSalvar.innerHTML = "Por favor, aguarde...";

  let formData = new FormData(form);

  let jsonObject = {};

  formData.forEach((value, key) => {
    const inputElement = form.elements[key]; // Obtém o elemento do formulário pelo nome

    if (inputElement && inputElement.type === "checkbox") {
      // Se for uma checkbox, armazena como booleano
      jsonObject[key] = inputElement.checked;
    } else {
      if (jsonObject[key]) {
        if (!Array.isArray(jsonObject[key])) {
          jsonObject[key] = [jsonObject[key]];
        }
        jsonObject[key].push(value);
      } else {
        jsonObject[key] = value;
      }
    }
  });

  console.log(jsonObject);

  if (!jsonObject.password || !jsonObject.confirmPassword) {
    Swal.fire({
      title: "Erro ao realizar cadastro",
      html: `
          <div>
              <span>O campo Senha e Confirmar Senha são obrigatórios.</span>
              <br><br>
              <ul>
                  <li>A senha deverá ter, no mínimo, 8 caracteres.</li>
                  <li>É recomendado que tenha, ao menos, um símbolo especial.</li>
                  <li>Não utilize seu nome e/ou sequência de números repetidos. </li>
              </ul>
          </div>
      `,
      icon: "error",
    });

    return null;
  }

  if (jsonObject.password.length < 8) {
    Swal.fire({
      title: "Erro ao realizar cadastro",
      html: `
        <div>
              <span>A senha é muito curta!</span>
        </div>
      `,
      icon: "error",
    });

    return null;
  }

  if (jsonObject.password !== jsonObject.confirmPassword) {
    Swal.fire({
      title: "Erro ao realizar cadastro",
      html: `
          <div>
              <span>As senhas não conferem.</span>
          </div>
      `,
      icon: "error",
    });
    return null;
  }

  handleSubmit(jsonObject);
});

const handleAddressCheckbox = (checkbox) => {
  const fields = document.querySelectorAll(
    "#cep, #street, #number, #district, #city"
  );
  fields.forEach((field) => {
    field.disabled = checkbox.checked;

    if (checkbox.checked) {
      field.removeAttribute("required");
    } else {
      field.setAttribute("required", "true");
    }
  });
};

/**
 * lida com o envio para API
 * @param {*} form
 */
const handleSubmit = async (form) => {
  if (!form || !form.personType) {
    Swal.fire({
      title: "Erro ao realizar cadastro",
      html: `
          <div>
              <span>Verifique o formulário de cadastro. Caso o erro persista, recarregue a página.</span>
          </div>
      `,
      icon: "error",
    });
  }

  let dto = {
    email: form.email,
    password: form.password,
    confirmPassword: form.confirmPassword,
    personType: form.personType,
    contact: {
      phone: form.phone,
      whatsapp: form.whatsapp,
    },
    address: {
      cep: form.cep,
      street: form.street,
      number: form.number,
      district: form.district,
      city: form.city,
      hideAddress: form.addressCheck,
    },
  };

  let endpoint = "";

  console.log(dto);

  if (form.personType === "fisica") {
    endpoint = `/api/persons`;
    dto.person = {
      cpf: form.cpf,
      rg: form.rg,
      name: form.fullName,
      birthDate: form.birthDate,
      gender: form.gender,
    };
  } else {
    endpoint = `/api/legal-entities`;
    dto.company = {
      cnpj: form.cnpj,
      subscriptionNumber: form.subscriptionNumber,
      contact: form.contact,
    };
  }

  const response = await fetch(endpoint, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(dto),
  });

  btnSalvar.innerHTML = "Salvar";
  btnSalvar.disabled = false;

  if (!response.ok) {
    const data = await response.json();
    displayErrors(data);
    Swal.fire({
      title: "Erro ao realizar cadastro",
      html: `
            <div>
                <span>Verifique o formulário de cadastro. ${
                  data.message ? data.message : ""
                }</span>
            </div>
        `,
      icon: "error",
    });
  }

  const data = await response.json();

  Swal.fire({
    toast: true,
    title: "Cadastro realizado com sucesso!",
    text: "Acesse o sistema com seu e-mail e senha.",
    icon: "success",
    confirmButtonColor: "#3085d6",
    confirmButtonText: "Ir para a tela de login!",
    allowOutsideClick: false,
  }).then((result) => {
    if (result.isConfirmed) {
      window.location.href = "/acesso";
    }
  });

  form.reset();
};

document.addEventListener("DOMContentLoaded", function () {
  const tabs = document.querySelectorAll(".nav-link"); // Todas as abas

  let currentStep = 0; // Começamos na primeira aba
  let isReadyToSave = false;

  function updateButtons() {
    btnAnterior.disabled = currentStep === 0; // Desabilita "Anterior" na primeira aba

    if (currentStep === tabs.length - 1) {
      isReadyToSave = true;
      btnProximo.setAttribute("style", "display: none");

      btnSalvar.removeAttribute("style");
    }

    if (isReadyToSave && currentStep !== tabs.length - 1) {
      isReadyToSave = false;
      btnProximo.removeAttribute("style");
      btnSalvar.setAttribute("style", "display: none");
    }
  }

  function validateCurrentStep() {
    const currentTabContent = document.querySelector(
      tabs[currentStep].dataset.bsTarget
    );
    const inputs = currentTabContent.querySelectorAll(
      "input[required], select[required]"
    );

    let allFilled = true;

    inputs.forEach((input) => {
      if (input.value.trim() === "" || input.value === "-1") {
        // Verifica se está vazio ou inválido
        input.classList.add("is-invalid"); // Adiciona borda vermelha
        allFilled = false;
      } else {
        input.classList.remove("is-invalid"); // Remove borda vermelha se preenchido
      }
    });

    return allFilled;
  }

  function changeTab(step) {
    if (step === 1 && !validateCurrentStep()) {
      return; // Se os campos não estiverem preenchidos, não avança
    }

    tabs[currentStep].classList.remove("active");
    document
      .querySelector(tabs[currentStep].dataset.bsTarget)
      .classList.remove("show", "active");

    currentStep += step;

    tabs[currentStep].classList.add("active");
    document
      .querySelector(tabs[currentStep].dataset.bsTarget)
      .classList.add("show", "active");

    updateButtons();
  }

  btnProximo.addEventListener("click", function () {
    if (currentStep < tabs.length - 1) {
      changeTab(1);
    }
  });

  btnAnterior.addEventListener("click", function () {
    if (currentStep > 0) {
      changeTab(-1);
    }
  });

  updateButtons(); // Atualiza os botões ao carregar a página
});

document.getElementById("tipoPessoa").addEventListener("change", function () {
  let tipo = this.value;

  let divPessoaFisica = document.getElementById("camposFisica");
  let divPessoaJuridica = document.getElementById("camposJuridica");
  let content = "";

  if (tipo === "fisica") {
    divPessoaFisica.innerHTML = "";
    divPessoaJuridica.innerHTML = "";

    content = `
        <div class="col-md-6 col-lg-3 mb-2">
            <div class="form-floating">
                <input type="text" class="form-control" name="cpf" id="cpf" placeholder="CPF" oninput="maskCPF(this)" minlength="14" maxlength="14" required/>
                <label for="cpf">CPF</label>
            </div>
        </div>
        <div class="col-md-6 col-lg-3 mb-2">
            <div class="form-floating">
                <input type="text" class="form-control" name="rg" id="rg" placeholder="RG" oninput="maskRG(this)" maxlength="12" required />
                <label for="rg">RG</label>
            </div>
        </div>
        <div class="col-md-6 mb-2">
            <div class="form-floating">
                <input type="text" class="form-control" name="fullName" id="nome" placeholder="Nome Completo" required />
                <label for="nome">Nome Completo</label>
            </div>
        </div>
        <div class="col-md-6 mb-2">
            <div class="form-floating">
                <select class="form-select" id="gender" name="gender" required>
                    <option value="-1" selected disabled>Selecione uma opção</option>
                    <option value="MALE">Masculino</option>
                    <option value="FEMALE">Feminino</option>
                    <option value="NON_BINARY">Não-binário</option>
                    <option value="AGENDER">Agênero</option>
                    <option value="GENDERFLUID">Gênero fluído</option>
                    <option value="OTHER">Outro / Prefiro não responder</option>
                </select>
                <label for="gender">Gênero</label>
            </div>
        </div>
        <div class="col-sm-12 col-md-12 col-lg-6 mb-2">
            <div class="form-floating">
                <input type="date" class="form-control" name="birthDate" id="dataNascimento" required />
                <label for="dataNascimento">Data de Nascimento</label>
            </div>
        </div>
    `;

    divPessoaFisica.innerHTML = content;
  } else {
    divPessoaFisica.innerHTML = "";
    divPessoaJuridica.innerHTML = "";

    content = `
        <div class="col-md-6 mb-2">
            <div class="form-floating">
                <input type="text" class="form-control" name="cnpj" id="cnpj" placeholder="CNPJ"  oninput="maskCNPJ(this)" required />
                <label for="cnpj">CNPJ</label>
            </div>
        </div>
        <div class="col-md-6 mb-2">
            <div class="form-floating">
                <input type="text" class="form-control" name="subscriptionNumber" id="inscricaoEstadual"
                    placeholder="Inscrição Estadual" required />
                <label for="inscricaoEstadual">Inscrição Estadual</label>
            </div>
        </div>
        <div class="col-md-12 mb-2">
            <div class="form-floating">
                <input type="text" class="form-control" name="contact" id="contatoEmpresa"
                    placeholder="Nome do Contato" required />
                <label for="contatoEmpresa">Nome do Contato da Empresa</label>
            </div>
        </div>
    `;

    divPessoaJuridica.innerHTML = content;
  }
});

function displayErrors(errors) {
  const errorContainer = document.getElementById("error-messages");
  errorContainer.innerHTML = ""; // Limpa os erros anteriores

  Object.entries(errors).forEach(([field, message]) => {
    // Criando um alerta do Bootstrap dinamicamente
    const alertDiv = document.createElement("div");
    alertDiv.className = "alert alert-danger alert-dismissible fade show";
    alertDiv.setAttribute("role", "alert");
    alertDiv.innerHTML = `
      <strong>${message}</strong>
      <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    `;
    errorContainer.appendChild(alertDiv);
  });
}
