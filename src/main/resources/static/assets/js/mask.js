function maskRG(input) {
    let value = input.value;

    // Remove tudo que não for número ou letra (X pode aparecer no final)
    value = value.replace(/\W/g, "");

    // Limita a 9 caracteres (8 números + 1 letra no final)
    value = value.slice(0, 9);

    // Adiciona os pontos e o traço conforme o usuário digita
    value = value.replace(/^(\d{2})(\d)/, "$1.$2");
    value = value.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3");
    value = value.replace(/^(\d{2})\.(\d{3})\.(\d{3})([\dXx])?/, "$1.$2.$3-$4");

    // Atualiza o valor do input
    input.value = value;
}

function maskCPF(input) {
    let value = input.value;

    // Remove tudo que não for número
    value = value.replace(/\D/g, "");

    // Limita a 11 caracteres
    value = value.slice(0, 11);

    // Adiciona os pontos e o traço conforme o usuário digita
    value = value.replace(/^(\d{3})(\d)/, "$1.$2");
    value = value.replace(/^(\d{3})\.(\d{3})(\d)/, "$1.$2.$3");
    value = value.replace(/^(\d{3})\.(\d{3})\.(\d{3})(\d)/, "$1.$2.$3-$4");

    // Atualiza o valor do input
    input.value = value;
}

function maskCNPJ(input) {
    let value = input.value;

    // Remove tudo que não for número
    value = value.replace(/\D/g, "");

    // Limita a 14 caracteres
    value = value.slice(0, 14);

    // Adiciona os pontos, barra e traço conforme o usuário digita
    value = value.replace(/^(\d{2})(\d)/, "$1.$2");
    value = value.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3");
    value = value.replace(/^(\d{2})\.(\d{3})\.(\d{3})(\d)/, "$1.$2.$3/$4");
    value = value.replace(/^(\d{2})\.(\d{3})\.(\d{3})\/(\d{4})(\d)/, "$1.$2.$3/$4-$5");

    input.value = value;
}

function maskCEP(input) {
    let value = input.value;

    // Remove tudo que não for número
    value = value.replace(/\D/g, "");

    // Limita a 8 caracteres
    value = value.slice(0, 8);

    // Formata no padrão CEP: 00000-000
    value = value.replace(/^(\d{5})(\d)/, "$1-$2");

    input.value = value;
}

function maskPhone(input) {
    let value = input.value;

    // Remove tudo que não for número
    value = value.replace(/\D/g, "");

    // Limita a 11 caracteres (para incluir celulares com DDD)
    value = value.slice(0, 11);

    // Aplica máscara para celular (com 9 na frente) ou telefone fixo (8 dígitos)
    if (value.length > 10) {
        // Formato celular: (00) 90000-0000
        value = value.replace(/^(\d{2})(\d{1})(\d{4})(\d{4})/, "($1) $2$3-$4");
    } else {
        // Formato fixo: (00) 0000-0000
        value = value.replace(/^(\d{2})(\d{4})(\d{4})/, "($1) $2-$3");
    }

    input.value = value;
}