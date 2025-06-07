package br.com.salvando.salvando.controller;

import br.com.salvando.salvando.entity.*;
import br.com.salvando.salvando.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    @Autowired
    private EventoRepository repo;

    @GetMapping
    public Page<Evento> listarEventos(
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude,
            Pageable pageable) {
        if (latitude != null && longitude != null) {
            return repo.findByLatitudeAndLongitude(latitude, longitude, pageable);
        }
        return repo.findAll(pageable);
    }
}