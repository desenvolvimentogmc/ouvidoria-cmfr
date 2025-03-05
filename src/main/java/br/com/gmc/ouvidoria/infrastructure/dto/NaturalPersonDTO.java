package br.com.gmc.ouvidoria.infrastructure.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import br.com.gmc.ouvidoria.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

/**
 * Representa pessoa física
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 */
public record NaturalPersonDTO(
    @CPF(message = "CPF inválido") 
    @Size(min = 14, message = "CPF inválido.") 
    String cpf,

    @Size(min = 14, message = "RG inválido")
    String rg,

    @NotNull (message = "Nome não pode ser nulo.")
    @NotBlank (message = "Nome não pode estar em branco.")
    @NotEmpty(message = "Nome não pode estar vazio.")
    @Size(min = 3, message = "Nome inválido. Preencha seu nome completo.") 
    String name,

    @NotNull (message = "Nome não pode ser nulo.")
    @NotBlank (message = "Nome não pode estar em branco.")
    @NotEmpty(message = "Nome não pode estar vazio.")
    @Past(message = "Data de nascimento deve estar no passado.")
    LocalDate birthDate,
    
    Gender gender
) {}
