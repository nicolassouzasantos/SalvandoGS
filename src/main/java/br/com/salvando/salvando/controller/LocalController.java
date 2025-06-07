package br.com.salvando.salvando.controller;

import br.com.salvando.salvando.entity.*;
import br.com.salvando.salvando.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/locais")
public class LocalController {
    @Autowired
    private LocalRepository repo;

    @GetMapping
    public List<Local> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Local salvar(@Valid @RequestBody Local local) {
        return repo.save(local);
    }

    @GetMapping("/{id}")
    public Local buscar(@PathVariable String id) {
        return repo.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        repo.deleteById(id);
    }
}
