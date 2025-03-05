package br.com.gmc.ouvidoria.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 */
public record ContactDTO(
    @NotNull(message = "O campo telefone não pode ser nulo.")
    @NotEmpty(message = "O campo telefone não pode estar vazio.")
    @NotBlank(message = "O campo telefone não pode estar em branco.")
    String phone,
    String whatsapp
) {}
