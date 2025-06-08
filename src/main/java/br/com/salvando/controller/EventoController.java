package br.com.salvando.controller;

import br.com.salvando.dto.EventoCreateDTO;
import br.com.salvando.dto.EventoResponseDTO;
import br.com.salvando.service.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventos")
@Tag(name = "Eventos", description = "Gerenciamento de eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    @Operation(summary = "Listar todos os eventos")
    public ResponseEntity<List<EventoResponseDTO>> getAllEventos() {
        List<EventoResponseDTO> eventos = eventoService.findAll();
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar evento por ID")
    public ResponseEntity<EventoResponseDTO> getEventoById(@PathVariable Long id) {
        Optional<EventoResponseDTO> evento = eventoService.findById(id);
        return evento.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sensor/{numeroSensor}")
    @Operation(summary = "Buscar eventos por sensor")
    public ResponseEntity<List<EventoResponseDTO>> getEventosBySensor(@PathVariable Long numeroSensor) {
        List<EventoResponseDTO> eventos = eventoService.findBySensor(numeroSensor);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/periodo")
    @Operation(summary = "Buscar eventos entre datas")
    public ResponseEntity<List<EventoResponseDTO>> getEventosEntreDatas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        List<EventoResponseDTO> eventos = eventoService.findEventosEntreDatas(inicio, fim);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/recentes")
    @Operation(summary = "Buscar eventos mais recentes")
    public ResponseEntity<List<EventoResponseDTO>> getEventosRecentes(
            @RequestParam(defaultValue = "10") int limit) {
        List<EventoResponseDTO> eventos = eventoService.findRecentEventos(limit);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/hoje")
    @Operation(summary = "Buscar eventos de hoje")
    public ResponseEntity<List<EventoResponseDTO>> getEventosHoje() {
        List<EventoResponseDTO> eventos = eventoService.findEventosHoje();
        return ResponseEntity.ok(eventos);
    }

    @PostMapping
    @Operation(summary = "Criar novo evento")
    public ResponseEntity<EventoResponseDTO> createEvento(@Valid @RequestBody EventoCreateDTO eventoCreateDTO) {
        try {
            EventoResponseDTO novoEvento = eventoService.save(eventoCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoEvento);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir evento")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        try {
            eventoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}