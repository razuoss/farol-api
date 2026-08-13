package io.github.razuoss.farol_da_fe.domain.devocional.service;

import io.github.razuoss.farol_da_fe.domain.devocional.model.Devocional;
import io.github.razuoss.farol_da_fe.domain.devocional.model.SolicitacaoDevocional;
import io.github.razuoss.farol_da_fe.domain.devocional.port.in.DevocionalUseCase;
import io.github.razuoss.farol_da_fe.domain.shared.port.out.GenAiPort;

public class DevocionalService implements DevocionalUseCase {

    private final GenAiPort genAiPort;

    public DevocionalService(GenAiPort genAiPort) {
        this.genAiPort = genAiPort;
    }

    @Override
    public Devocional gerarDevocional(SolicitacaoDevocional solicitacao) {
        if (solicitacao == null || solicitacao.tema() == null || solicitacao.tema().trim().isEmpty()) {
            throw new IllegalArgumentException("Solicitacao não pode ser nula ou vazia");
        }
        return genAiPort.gerar(solicitacao);
    }
}
