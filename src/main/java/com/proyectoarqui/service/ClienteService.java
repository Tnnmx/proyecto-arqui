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

    /** NRM-MC: el cliente puede actualizar su nombre, domicilio y fecha
     *  de nacimiento, identificando su registro por su CURP (ERS
     *  requisito 5). La CURP no se modifica: es el identificador del
     *  registro. Los campos que vienen en null no se tocan, para no
     *  borrar datos en una modificación parcial. */
    public Cliente modificacion(Long id, Cliente datos) {
        return repository.findById(id)
                .map(c -> {
                    if (datos.getNombres() != null) {
                        c.setNombres(datos.getNombres());
                    }
                    if (datos.getPrimerApellido() != null) {
                        c.setPrimerApellido(datos.getPrimerApellido());
                    }
                    if (datos.getSegundoApellido() != null) {
                        c.setSegundoApellido(datos.getSegundoApellido());
                    }
                    if (datos.getDireccion() != null) {
                        c.setDireccion(datos.getDireccion());
                    }
                    if (datos.getFechaNacimiento() != null) {
                        c.setFechaNacimiento(datos.getFechaNacimiento());
                    }
                    return repository.save(c);
                })
                .orElse(null);
    }
}
