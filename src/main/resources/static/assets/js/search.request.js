document.getElementById("search-input").addEventListener("keypress", function(event) {
    if (event.key === "Enter") { // Verifica se a tecla pressionada foi "Enter"
        event.preventDefault(); // Evita o envio do formulário, caso esteja dentro de um form
        handleSearch(this.value); // Chama a função de pesquisa passando o valor do input
    }
});

const handleSearch = async (protocol) => {

    showLoading("Verificando protocolo.", "Por favor, aguarde...");

    const response = await fetch(`/api/requests?requestId=${protocol}`);

    if(response.ok) {
        window.location.href = `/solicitacoes/${protocol}`;
        return ;
    } 

    showError(`O protocolo de n.º ${protocol} não foi localizado.`, "Verifique se você digitou o valor corretamente.");

}