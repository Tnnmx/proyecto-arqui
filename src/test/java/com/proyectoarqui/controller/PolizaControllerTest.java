package com.proyectoarqui.controller;

import com.proyectoarqui.domain.Poliza;
import com.proyectoarqui.service.PolizaService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** Prueba de integracion ligera 3.4: controlador + servicio mock, sin
 *  base de datos (MockMvc standalone, sin @MockBean para que no dependa
 *  de la version de Spring Boot). Casos: NRM-CP lectura, NRM-AP alta. */
class PolizaControllerTest {
    private final PolizaService service = mock(PolizaService.class);
    private final MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(new PolizaController(service)).build();

    @Test
    void getPolizasDevuelveLaLista() throws Exception {
        Poliza poliza = new Poliza();
        poliza.setTipo(0);
        when(service.consultarTodas()).thenReturn(List.of(poliza));
        mockMvc.perform(get("/polizas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tipo").value(0));
    }

    @Test
    void postPolizasCreaUnaPoliza() throws Exception {
        Poliza poliza = new Poliza();
        poliza.setTipo(1);
        when(service.alta(org.mockito.ArgumentMatchers.any(Poliza.class)))
                .thenReturn(poliza);
        mockMvc.perform(post("/polizas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"tipo\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo").value(1));
    }
}
