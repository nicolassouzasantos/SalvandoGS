package br.com.salvando.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoResponseDTO {
    private Long idEvento;
    private LocalDateTime dataHora;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Long idSensor;
    private SensorDTO sensor;
}
