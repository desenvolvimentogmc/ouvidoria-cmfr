package br.com.gmc.ouvidoria.infrastructure.dto;

import br.com.gmc.ouvidoria.entity.model.User;

public record TokenRecoveredDTO(User user, String token) {
}
