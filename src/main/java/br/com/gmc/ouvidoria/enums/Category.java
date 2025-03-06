package br.com.gmc.ouvidoria.enums;

import lombok.Getter;

public enum Category {
    
    DENUNCIA("Denúncia"),
    DOACAO("Doação"),
    ELOGIO("Elogio"),
    INFORMACAO("Informação"),
    SOLICITACAO("Solicitação"),
    RECLAMACAO("Reclamação"),
    SUGESTAO("Sugestão");

    @Getter
    private String description;

    Category(String description) {
        this.description = description;
    }

}
