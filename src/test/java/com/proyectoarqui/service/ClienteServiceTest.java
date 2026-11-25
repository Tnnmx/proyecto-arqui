package com.proyectoarqui.service;

import com.proyectoarqui.domain.Cliente;
import com.proyectoarqui.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/** Pruebas unitarias 3.4: ClienteService, sin base de datos.
 *  Casos: NRM-AC alta, NRM-MC modificacion.
 *  OJO: hoy Cliente solo tiene id, asi que modificacion guarda el
 *  cliente como estaba (TODO en ClienteService). Esta prueba deja
 *  ese comportamiento documentado: cuando se agreguen los atributos
 *  de la ERS, aqui debe fallar hasta copiar los campos. */
class ClienteServiceTest {
    private final ClienteRepository repository = mock(ClienteRepository.class);
    private final ClienteService service = new ClienteService(repository);

    @Test
    void altaGuardaElCliente() {
        Cliente cliente = new Cliente();
        when(repository.save(cliente)).thenReturn(cliente);
        assertNotNull(service.alta(cliente));
        verify(repository).save(cliente);
    }

    @Test
    void modificacionDeIdInexistenteDevuelveNull() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertNull(service.modificacion(99L, new Cliente()));
    }

    @Test
    void modificacionGuardaElClienteExistente() {
        Cliente guardado = new Cliente();
        when(repository.findById(1L)).thenReturn(Optional.of(guardado));
        when(repository.save(guardado)).thenReturn(guardado);
        assertNotNull(service.modificacion(1L, new Cliente()));
        verify(repository).save(guardado);
    }
}
