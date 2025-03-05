package br.com.gmc.ouvidoria.infrastructure.controller;

import br.com.gmc.ouvidoria.configuration.ClientSettings;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    private final ClientSettings clientSettings;

    public GlobalControllerAdvice(ClientSettings clientSettings) {
        this.clientSettings = clientSettings;
    }

    @ModelAttribute("settings")
    public ClientSettings settings() {
        return this.clientSettings;
    }
}
