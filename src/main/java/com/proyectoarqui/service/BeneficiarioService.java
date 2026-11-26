package com.proyectoarqui.service;

import com.proyectoarqui.domain.Beneficiario;
import com.proyectoarqui.repository.BeneficiarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/** Casos: NRM-AB alta de beneficiario, NRM-CB consulta de beneficiario. */
@Service
public class BeneficiarioService {
    private final BeneficiarioRepository repository;

    public BeneficiarioService(BeneficiarioRepository repository) {
        this.repository = repository;
    }

    public List<Beneficiario> consultarTodos() {
        return repository.findAll();
    }

    public Beneficiario consultarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Beneficiario alta(Beneficiario beneficiario) {
        return repository.save(beneficiario);
    }
}
