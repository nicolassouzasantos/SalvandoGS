package br.com.salvando.salvando.service;

import br.com.salvando.salvando.entity.*;
import br.com.salvando.salvando.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repo;

    public List<Usuario> listar() {
        return repo.findAll();
    }

    public Usuario salvar(Usuario u) {
        return repo.save(u);
    }
}