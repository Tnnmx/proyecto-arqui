package com.proyectoarqui.controller;

import com.proyectoarqui.domain.Cliente;
import com.proyectoarqui.service.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/** POST /clientes — NRM-AC alta; PUT /clientes/{id} — NRM-MC;
 *  GET — apoyo a NRM-CP / NRM-CB. */
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService service;
    public ClienteController(ClienteService service) { this.service = service; }
    @GetMapping public List<Cliente> listar() { return service.consultarTodos(); }
    @GetMapping("/{id}")
    public Cliente porId(@PathVariable Long id) {
        return service.consultarPorId(id);
    }
    @PostMapping
    public Cliente crear(@RequestBody Cliente cliente) {
        return service.alta(cliente);
    }
    @PutMapping("/{id}")
    public Cliente modificar(@PathVariable Long id, @RequestBody Cliente cliente) {
        return service.modificacion(id, cliente);
    }
}
