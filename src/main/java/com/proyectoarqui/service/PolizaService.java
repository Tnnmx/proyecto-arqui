package com.proyectoarqui.service;

import com.proyectoarqui.domain.Poliza;
import com.proyectoarqui.repository.PolizaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/** Servicios de aplicación, uno por caso de uso (Práctica 1, 4.2).
 *  Casos: NRM-AP alta, NRM-CP consulta, NRM-MP modificación,
 *  NRM-BP baja, PRM-GP gestión. El controlador no lleva reglas. */
@Service
public class PolizaService {
    private final PolizaRepository repository;
    public PolizaService(PolizaRepository repository) { this.repository = repository; }
    public List<Poliza> consultarTodas() { return repository.findAll(); }
    public Poliza consultarPorId(Long id) { return repository.findById(id).orElse(null); }
    public Poliza alta(Poliza poliza) { return repository.save(poliza); }
    public Poliza modificacion(Long id, Poliza datos) {
        return repository.findById(id)
                .map(p -> { p.setTipo(datos.getTipo()); return repository.save(p); })
                .orElse(null);
    }
    public void baja(Long id) { repository.deleteById(id); }
}
