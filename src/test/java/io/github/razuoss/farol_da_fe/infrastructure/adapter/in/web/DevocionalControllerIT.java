package io.github.razuoss.farol_da_fe.infrastructure.adapter.in.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@ActiveProfiles("test")
class DevocionalControllerIT {

    private MockMvc mockMvc;

    @Autowired
    private DevocionalController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void dadoPayloadValido_quandoPost_entaoRetorna200EJSON() throws Exception {
        String jsonRequest = "{\"solicitacao\": \"mensagem de esperança\"}";

        mockMvc.perform(post("/v1/devocional")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").exists());
    }

    @Test
    void dadoPayloadSemSolicitacao_quandoPost_entaoRetorna400() throws Exception {
        String jsonRequest = "{}";

        mockMvc.perform(post("/v1/devocional")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    void dadoPayloadComSolicitacaoVazia_quandoPost_entaoRetorna400() throws Exception {
        String jsonRequest = "{\"solicitacao\": \"   \"}";

        mockMvc.perform(post("/v1/devocional")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }
}
