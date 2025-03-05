package br.com.gmc.ouvidoria.entity.model;

import java.time.LocalDate;

import br.com.gmc.ouvidoria.enums.Gender;
import br.com.gmc.ouvidoria.infrastructure.dto.RegisterDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe que representa uma Pessoa Física
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see User
 * @see Address
 * @see RegisterDTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;
    
    @Column(nullable = false, length = 14, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String rg;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(nullable = false, length = 15)
    private String whatsapp;

    private String addressNumber;

    @ManyToOne
    @JoinColumn(name = "address_cep")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    public Person(RegisterDTO dto) throws IllegalArgumentException {
        this.name = dto.person().name();
        this.birthDate = dto.person().birthDate();
        this.cpf = dto.person().cpf();
        this.rg = dto.person().rg();
        this.phone = dto.contact().phone();
        this.whatsapp = dto.contact().whatsapp();
        this.gender = dto.person().gender();
        this.addressNumber = dto.address().number();
        this.address = new Address(dto.address());
        this.user = new User(dto);
    }
}
