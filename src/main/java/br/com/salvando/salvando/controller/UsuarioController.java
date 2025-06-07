package br.com.salvando.salvando.controller;

import br.com.salvando.salvando.entity.*;
import br.com.salvando.salvando.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repo;

    @GetMapping
    public List<Usuario> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Usuario salvar(@Valid @RequestBody Usuario usuario) {
        return repo.save(usuario);
    }
}

