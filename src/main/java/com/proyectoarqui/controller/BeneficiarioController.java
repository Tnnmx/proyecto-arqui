package com.proyectoarqui.controller;

import com.proyectoarqui.domain.Beneficiario;
import com.proyectoarqui.service.BeneficiarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/** POST /beneficiarios — NRM-AB alta; GET — NRM-CB consulta. */
@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController {
    private final BeneficiarioService service;

    public BeneficiarioController(BeneficiarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Beneficiario> listar() {
        return service.consultarTodos();
    }

    @GetMapping("/{id}")
    public Beneficiario porId(@PathVariable Long id) {
        return service.consultarPorId(id);
    }

    @PostMapping
    public Beneficiario crear(@RequestBody Beneficiario beneficiario) {
        return service.alta(beneficiario);
    }
}
