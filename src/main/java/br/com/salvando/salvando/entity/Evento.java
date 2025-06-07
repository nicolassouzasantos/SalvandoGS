package br.com.salvando.salvando.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;

    @NotNull
    private LocalDateTime dataHora;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;
}