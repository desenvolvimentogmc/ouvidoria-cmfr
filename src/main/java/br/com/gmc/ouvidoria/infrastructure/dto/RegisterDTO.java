package br.com.gmc.ouvidoria.infrastructure.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 */
public record RegisterDTO(
    @NotNull(message = "O campo e-mail não pode ser nulo.")
    @NotEmpty(message = "O campo e-mail não pode estar vazio.")
    @NotBlank(message = "O campo e-mail não pode estar em branco.")
    @Email(message = "E-mail inválido.")
    String email,

    @NotNull(message = "O campo senha não pode ser nulo.")
    @NotEmpty(message = "O campo senha não pode estar vazio.")
    @NotBlank(message = "O campo senha não pode estar em branco.")
    String password,

    @NotNull(message = "O campo confirmar senha não pode ser nulo.")
    @NotEmpty(message = "O campo confirmar senha não pode estar vazio.")
    @NotBlank(message = "O campo confirmar senha não pode estar em branco.")
    String confirmPassword,

    String personType,

    CompanyRegisterDTO company,
    NaturalPersonDTO person,
    ContactDTO contact,
    AddressDTO address
) {}
