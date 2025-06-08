package br.com.salvando.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoDTO {

    private Long idEvento;

    private LocalDateTime dataHora;

    private BigDecimal latitude;

    private BigDecimal longitude;

    @NotNull(message = "ID do sensor é obrigatório")
    private Long idSensor;
}