package br.com.salvando.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorDTO {

    @NotNull(message = "Número do sensor é obrigatório")
    private Long numeroSensor;

    @NotNull(message = "Latitude é obrigatória")
    private BigDecimal latitude;

    @NotNull(message = "Longitude é obrigatória")
    private BigDecimal longitude;
}