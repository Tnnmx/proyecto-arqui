package com.proyectoarqui.controller;

import com.proyectoarqui.domain.Poliza;
import com.proyectoarqui.service.PolizaService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/** Lectura (15 pts): GET /polizas, GET /polizas/{id} — NRM-CP.
 *  Escritura (10 pts): POST — NRM-AP; PUT — NRM-MP; DELETE — NRM-BP. */
@RestController
@RequestMapping("/polizas")
public class PolizaController {
    private final PolizaService service;

    public PolizaController(PolizaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Poliza> listar() {
        return service.consultarTodas();
    }

    @GetMapping("/{id}")
    public Poliza porId(@PathVariable Long id) {
        return service.consultarPorId(id);
    }

    @PostMapping
    public Poliza crear(@RequestBody Poliza poliza) {
        return service.alta(poliza);
    }

    @PutMapping("/{id}")
    public Poliza modificar(@PathVariable Long id, @RequestBody Poliza poliza) {
        return service.modificacion(id, poliza);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.baja(id);
    }
}
