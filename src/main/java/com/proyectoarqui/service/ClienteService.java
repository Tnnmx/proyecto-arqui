package com.proyectoarqui.service;

import com.proyectoarqui.domain.Cliente;
import com.proyectoarqui.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/** Casos: NRM-AC alta de cliente, NRM-MC modificación de cliente. */
@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> consultarTodos() {
        return repository.findAll();
    }

    public Cliente consultarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Cliente alta(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente modificacion(Long id, Cliente datos) {
        // TODO [confirmar con ERS]: al agregar atributos a Cliente, copiarlos
        // aquí desde `datos`, como hace PolizaService con el tipo. Hoy Cliente
        // solo tiene id, así que no hay nada que copiar.
        return repository.findById(id).map(c -> repository.save(c)).orElse(null);
    }
}
