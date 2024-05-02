package com.ms.client.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Component
@Table(name = "tb_client")
public class Client implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idClient;
    private String firstName;
    private String lastName;
    private String cpf;
    private String email;
    private String phone;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)   // name = nome da coluna criada na tabela cliente no db. Ref prop Address
    @JoinColumn(name = "address_id", referencedColumnName = "idAddress")
    private Address address;

}
