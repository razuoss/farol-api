package io.github.razuoss.farol_da_fe.domain.shared.port.out;

import io.github.razuoss.farol_da_fe.domain.devocional.model.Devocional;
import io.github.razuoss.farol_da_fe.domain.devocional.model.SolicitacaoDevocional;

public interface GenAiPort {
    Devocional gerar(SolicitacaoDevocional solicitacao);
}
