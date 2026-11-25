package com.proyectoarqui.service;

import com.proyectoarqui.domain.Poliza;
import com.proyectoarqui.repository.PolizaRepository;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/** Pruebas unitarias 3.4: PolizaService, sin base de datos (repositorio mock).
 *  Casos: NRM-AP alta, NRM-CP consulta, NRM-MP modificacion, NRM-BP baja. */
class PolizaServiceTest {
    private final PolizaRepository repository = mock(PolizaRepository.class);
    private final PolizaService service = new PolizaService(repository);

    @Test
    void altaGuardaLaPoliza() {
        Poliza poliza = new Poliza();
        poliza.setTipo(1);
        when(repository.save(poliza)).thenReturn(poliza);
        assertEquals(1, service.alta(poliza).getTipo());
        verify(repository).save(poliza);
    }

    @Test
    void consultarTodasDevuelveLaLista() {
        Poliza poliza = new Poliza();
        poliza.setTipo(0);
        when(repository.findAll()).thenReturn(List.of(poliza));
        assertEquals(1, service.consultarTodas().size());
    }

    @Test
    void consultarPorIdInexistenteDevuelveNull() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertNull(service.consultarPorId(99L));
    }

    @Test
    void modificacionCopiaElTipo() {
        Poliza guardada = new Poliza();
        guardada.setTipo(0);
        Poliza datos = new Poliza();
        datos.setTipo(2);
        when(repository.findById(1L)).thenReturn(Optional.of(guardada));
        when(repository.save(guardada)).thenReturn(guardada);
        assertEquals(2, service.modificacion(1L, datos).getTipo());
    }

    @Test
    void modificacionDeIdInexistenteDevuelveNull() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertNull(service.modificacion(99L, new Poliza()));
    }

    @Test
    void bajaLlamaADeleteById() {
        service.baja(1L);
        verify(repository).deleteById(1L);
    }
}
