package com.proyectoarqui.repository;

import com.proyectoarqui.domain.Beneficiario;
import com.proyectoarqui.domain.Poliza;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** Prueba de integracion 3.4: repositorios JPA contra H2 en memoria
 *  (ver src/test/resources/application.properties). No toca PostgreSQL.
 *  Casos: NRM-AP alta de poliza, NRM-AB alta de beneficiario ligado. */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PolizaRepositoryTest {
    @Autowired
    private PolizaRepository polizaRepository;
    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Test
    void guardaPolizaYBeneficiarioLigado() {
        Poliza poliza = new Poliza();
        poliza.setTipo(2);
        Poliza guardada = polizaRepository.save(poliza);
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setPoliza(guardada);
        guardada.getBeneficiarios().add(beneficiario);
        beneficiarioRepository.save(beneficiario);
        Poliza leida = polizaRepository.findById(guardada.getId()).orElseThrow();
        assertEquals(2, leida.getTipo());
        assertEquals(1, leida.getBeneficiarios().size());
    }
}
