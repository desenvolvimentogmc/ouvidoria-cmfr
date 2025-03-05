package br.com.gmc.ouvidoria.entity.model;

import br.com.gmc.ouvidoria.infrastructure.dto.AddressDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe que representa um endereço no sistema
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see AddressDTO
 */
@Entity(name = "tb_addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    private String cep;

    private String street;

    private String district;

    private String city;

    public Address(AddressDTO dto) {
        if(!dto.hideAddress()) {
            this.cep = dto.cep().replaceAll("-", "");
            this.street = dto.street();
            this.district = dto.district();
            this.city = dto.city();
        }
    }
    
}
