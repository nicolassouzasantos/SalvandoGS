package br.com.salvando.salvando.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Local {
    @Id
    private String numeroSensor;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;
}
