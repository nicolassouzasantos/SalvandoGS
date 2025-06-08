package br.com.salvando.controller;

import br.com.salvando.dto.SensorDTO;
import br.com.salvando.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sensores")
@Tag(name = "Sensores", description = "Gerenciamento de sensores")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    @Operation(summary = "Listar todos os sensores")
    public ResponseEntity<List<SensorDTO>> getAllSensores() {
        List<SensorDTO> sensores = sensorService.findAll();
        return ResponseEntity.ok(sensores);
    }

    @GetMapping("/{numeroSensor}")
    @Operation(summary = "Buscar sensor por número")
    public ResponseEntity<SensorDTO> getSensorById(@PathVariable Long numeroSensor) {
        Optional<SensorDTO> sensor = sensorService.findById(numeroSensor);
        return sensor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/area")
    @Operation(summary = "Buscar sensores em uma área geográfica")
    public ResponseEntity<List<SensorDTO>> getSensoresInArea(
            @RequestParam BigDecimal latMin,
            @RequestParam BigDecimal latMax,
            @RequestParam BigDecimal lonMin,
            @RequestParam BigDecimal lonMax) {
        List<SensorDTO> sensores = sensorService.findSensorsInArea(latMin, latMax, lonMin, lonMax);
        return ResponseEntity.ok(sensores);
    }

    @PostMapping
    @Operation(summary = "Criar novo sensor")
    public ResponseEntity<SensorDTO> createSensor(@Valid @RequestBody SensorDTO sensorDTO) {
        try {
            SensorDTO novoSensor = sensorService.save(sensorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoSensor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{numeroSensor}")
    @Operation(summary = "Atualizar sensor")
    public ResponseEntity<SensorDTO> updateSensor(@PathVariable Long numeroSensor,
                                                  @Valid @RequestBody SensorDTO sensorDTO) {
        try {
            SensorDTO sensorAtualizado = sensorService.update(numeroSensor, sensorDTO);
            return ResponseEntity.ok(sensorAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{numeroSensor}")
    @Operation(summary = "Excluir sensor")
    public ResponseEntity<Void> deleteSensor(@PathVariable Long numeroSensor) {
        try {
            sensorService.deleteById(numeroSensor);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}