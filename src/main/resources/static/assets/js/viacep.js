const searchCep = async (input) => {
    let value = input.value.replace("-", "");

    if(value.length < 8) {
        return ;
    }

    let street = document.getElementById('street');
    let district = document.getElementById('district');
    let city = document.getElementById('city');
    let number = document.getElementById('number');

    street.setAttribute("readonly", true);
    district.setAttribute("readonly", true);
    city.setAttribute("readonly", true);
    number.focus();


    const response = await fetch(`//viacep.com.br/ws/${value}/json/`);

    if(response.ok) {

        const data = await response.json();

        street.value = data.logradouro;
        district.value = data.bairro;
        city.value = data.localidade
    }

}