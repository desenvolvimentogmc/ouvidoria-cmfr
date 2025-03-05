package br.com.gmc.ouvidoria.enums;

import lombok.Getter;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 */
@Getter
public enum Gender {
    MALE("Masculino"),
    FEMALE("Feminino"),
    NON_BINARY("Não-binário"),
    AGENDER("Agênero"),
    GENDERFLUID("Gênero fluído"),
    OTHER("Outro/Prefiro não identificar");

    private final String description;

    Gender(String description) {
        this.description = description;
    }
}