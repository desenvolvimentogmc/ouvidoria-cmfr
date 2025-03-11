package br.com.gmc.ouvidoria.infrastructure.dto;

import br.com.gmc.ouvidoria.enums.Status;

public record RequestsByStatusDTO(
    Status status,
    Long quantity
) {}
