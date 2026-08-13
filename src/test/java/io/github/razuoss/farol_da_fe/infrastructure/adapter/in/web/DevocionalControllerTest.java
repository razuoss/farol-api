package io.github.razuoss.farol_da_fe.infrastructure.adapter.in.web;

import io.github.razuoss.farol_da_fe.domain.devocional.model.Devocional;
import io.github.razuoss.farol_da_fe.domain.devocional.model.SolicitacaoDevocional;
import io.github.razuoss.farol_da_fe.domain.devocional.port.in.DevocionalUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DevocionalControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DevocionalUseCase devocionalUseCase;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new DevocionalController(devocionalUseCase)).build();
    }

    @Test
    void deveGerarDevocional() throws Exception {
        Devocional devocional = new Devocional("Titulo", "Texto", "Contexto", "Analise", "Aplicacao", "Oracao", "Aviso");
        when(devocionalUseCase.gerarDevocional(any(SolicitacaoDevocional.class))).thenReturn(devocional);

        mockMvc.perform(post("/v1/devocional")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "solicitacao": "teste"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Titulo"))
                .andExpect(jsonPath("$.textoChave").value("Texto"));
    }
}
