package br.com.gmc.ouvidoria.entity.model;

import br.com.gmc.ouvidoria.infrastructure.dto.RegisterDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe que representa Pessoa Jurídica
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see Address
 * @see User
 * @see RegisterDTO
 */
@Entity(name = "tb_legal_entities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LegalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cnpj;

    private String subscriptionId;

    private String contactName;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(nullable = false, length = 15)
    private String whatsapp;

    private String addressNumber;

    @ManyToOne
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    public LegalEntity(RegisterDTO dto) {
        this.cnpj = dto.company().cnpj();
        this.subscriptionId = dto.company().subscriptionNumber();
        this.contactName = dto.company().contact();
        this.phone = dto.contact().phone();
        this.whatsapp = dto.contact().whatsapp();
        this.addressNumber = dto.address().number();
        this.address = new Address(dto.address());
        this.user = new User(dto);
    }
    
}
