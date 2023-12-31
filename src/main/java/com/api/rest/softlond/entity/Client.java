package com.api.rest.softlond.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_client")
    @NotBlank(message = "Agregue el nombre del cliente")
    @Size(min = 2, max = 12)
    private String nameClient;

    @Column(name = "email_client",unique = true)
    @NotBlank
    @Email
    private String emailClient;

    @Column(name = "phone_number")
    @NotNull
    private Integer phoneNumber;

    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Sale> sales = new ArrayList<>();

}
