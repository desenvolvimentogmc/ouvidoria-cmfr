package br.com.gmc.ouvidoria.infrastructure.dto;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 */
public record CompanyRegisterDTO(
    @CNPJ(message = "CNPJ inválido.")
    @NotNull(message = "CPNJ não pode ser nulo")
    @NotBlank(message = "CNPJ não pode estar em branco")
    @NotEmpty(message = "CNPJ não pode estar vazio")
    String cnpj,

    @NotNull(message = "Inscrição Estadual não pode ser nulo")
    @NotBlank(message = "Inscrição EstadualPJ não pode estar em branco")
    @NotEmpty(message = "Inscrição Estadual não pode estar vazio")
    String subscriptionNumber,

    @NotNull(message = "Nome do contato não pode ser nulo")
    @NotBlank(message = "Nome do contato não pode estar em branco")
    @NotEmpty(message = "Nome do contato não pode estar vazio")
    String contact
) {
    
}
