package com.ms.client.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idAddress;
    private String streetName;
    private String houseNumber;
    private String zipCode;
    @JsonIgnore
    @OneToOne(mappedBy = "address") //ref prop Client
    private Client client;
}
