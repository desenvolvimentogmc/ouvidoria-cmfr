document.getElementById("search-input").addEventListener("keypress", function(event) {
    if (event.key === "Enter") { // Verifica se a tecla pressionada foi "Enter"
        event.preventDefault(); // Evita o envio do formulário, caso esteja dentro de um form
        handleSearch(this.value); // Chama a função de pesquisa passando o valor do input
    }
});

document.getElementById("search-input").addEventListener("input", function (e) {

    let input = e.target.value;

    // Remove tudo que não for número
    input = input.replace(/\D/g, "");

    // Aplica a máscara para o formato 202503061012-81887
    if (input.length <= 12) {
        input = input.replace(
            /(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})?/,
            "$1$2$3$4$5"
        );
    } else {
        input = input.replace(
            /(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{5})/,
            "$1$2$3$4$5-$6"
        );
    }

    // Limita o número de caracteres a 16 (incluindo o hífen)
    if (input.length > 18) {
        input = input.substring(0, 18);
    }

    // Atualiza o valor do campo de entrada com a máscara
    e.target.value = input;
});

const handleSearch = async (protocol) => {

    showLoading("Verificando protocolo.", "Por favor, aguarde...");

    let param = protocol.includes('-') ? "protocol" : "requestId"

    const response = await fetch(`/api/requests/search?${param}=${protocol}`);

    if(response.ok) {
        const data = await response.json();
        window.location.href = `/solicitacoes/${data.message}`;
        return ;
    } 

    showError(`O protocolo de n.º ${protocol} não foi localizado.`, "Verifique se você digitou o valor corretamente.");

}