package br.com.salvando.salvando.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Usuario {
    @Id
    @Email
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    private String nome;

    private String telefone;
    private String endereco;
}