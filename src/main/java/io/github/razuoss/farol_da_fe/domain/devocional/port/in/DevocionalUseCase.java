package io.github.razuoss.farol_da_fe.domain.devocional.port.in;

import io.github.razuoss.farol_da_fe.domain.devocional.model.Devocional;
import io.github.razuoss.farol_da_fe.domain.devocional.model.SolicitacaoDevocional;

public interface DevocionalUseCase {
    Devocional gerarDevocional(SolicitacaoDevocional solicitacao);
}
