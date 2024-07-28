package com.PUB_Online.PUB.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "garcons")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Garcom {
    public static final String TABLE_NAME = "garcons";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "garcom_id", unique = true)
    private Long id;

    @Column(name = "nome", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String nome;

    @Column(name = "cpf", length = 12, unique = true, nullable = false, updatable = false)
    @NotBlank
    private String cpf;

    @ElementCollection
    @CollectionTable(name = "garcom_telefones")
    @Column(name = "telefone")
    private List<String> telefone;

    @Column(name = "username", length = 100, unique = true, nullable = false, updatable = false)
    @NotBlank
    @Size(min = 4, max = 64)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
    @Column(name = "password", length = 127, nullable = false)
    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

}