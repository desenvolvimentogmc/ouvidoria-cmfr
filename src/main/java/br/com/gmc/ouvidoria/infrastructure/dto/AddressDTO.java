package br.com.gmc.ouvidoria.infrastructure.dto;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 */
public record AddressDTO(
    String cep,
    String street,
    String number,
    String district,
    String city,
    boolean hideAddress
) {}
