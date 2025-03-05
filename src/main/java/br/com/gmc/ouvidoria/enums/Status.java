package br.com.gmc.ouvidoria.enums;

import lombok.Getter;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 */
@Getter
public enum Status {
    OPEN("Aberto"),
    UNDER_ANALYSIS("Em análise"),
    ANSWERED("Respondido"),
    APPEAL("Em recurso"),
    CLOSED("Finalizado");

    private final String description;

    Status(String description) {
        this.description = description;
    }
}
