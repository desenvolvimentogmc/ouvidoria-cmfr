package br.com.gmc.ouvidoria.infrastructure.dto;

import java.time.LocalDateTime;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 */
public record ResponseExceptionHandlerDTO(
    String message,
    String error,
    LocalDateTime localDateTime
) {}