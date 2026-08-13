package io.github.razuoss.farol_da_fe.infrastructure.adapter.out.genai;

import io.github.razuoss.farol_da_fe.domain.devocional.model.Devocional;
import io.github.razuoss.farol_da_fe.domain.devocional.model.SolicitacaoDevocional;
import io.github.razuoss.farol_da_fe.domain.shared.port.out.GenAiPort;
import org.springframework.stereotype.Component;

@Component
public class MockGenAiAdapter implements GenAiPort {
    @Override
    public Devocional gerar(SolicitacaoDevocional solicitacao) {
        return new Devocional(
            "Mock: " + solicitacao.tema(),
            "Filipenses 4:13 (NVT)",
            "Contexto de Paulo na prisão...",
            "Análise do texto bíblico...",
            "Aplicação prática para os dias de hoje.",
            "Oração de dependência.",
            "Aviso pastoral: procure sua igreja local."
        );
    }
}
