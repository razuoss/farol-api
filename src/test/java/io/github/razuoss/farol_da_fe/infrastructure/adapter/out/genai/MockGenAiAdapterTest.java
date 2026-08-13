package io.github.razuoss.farol_da_fe.infrastructure.adapter.out.genai;

import io.github.razuoss.farol_da_fe.domain.devocional.model.Devocional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MockGenAiAdapterTest {

    @Test
    void deveGerarDevocionalMockado() {
        MockGenAiAdapter adapter = new MockGenAiAdapter();
        Devocional devocional = adapter.gerar(new io.github.razuoss.farol_da_fe.domain.devocional.model.SolicitacaoDevocional("teste"));
        
        assertNotNull(devocional);
        assertTrue(devocional.titulo().contains("Mock: teste"));
        assertNotNull(devocional.textoChave());
    }
}
