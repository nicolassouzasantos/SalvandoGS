package br.com.salvando.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "EVENTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EVENTO")
    private Long idEvento;

    @NotNull(message = "Data e hora são obrigatórias")
    @Column(name = "DATA_HORA", nullable = false)
    private LocalDateTime dataHora;

    @NotNull(message = "Latitude é obrigatória")
    @Column(name = "LATITUDE", nullable = false, precision = 10, scale = 8)
    private BigDecimal latitude;

    @NotNull(message = "Longitude é obrigatória")
    @Column(name = "LONGITUDE", nullable = false, precision = 11, scale = 8)
    private BigDecimal longitude;

    @NotNull(message = "Sensor é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SENSOR", nullable = false, referencedColumnName = "NUMERO_SENSOR")
    private Sensor sensor;

    @PrePersist
    public void prePersist() {
        if (dataHora == null) {
            dataHora = LocalDateTime.now();
        }
        if (sensor != null && latitude == null && longitude == null) {
            this.latitude = sensor.getLatitude();
            this.longitude = sensor.getLongitude();
        }
    }
}