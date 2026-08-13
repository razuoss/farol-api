package io.github.razuoss.farol_da_fe.infrastructure.adapter.in.web;

import io.github.razuoss.farol_da_fe.domain.devocional.model.Devocional;
import io.github.razuoss.farol_da_fe.domain.devocional.model.SolicitacaoDevocional;
import io.github.razuoss.farol_da_fe.domain.devocional.port.in.DevocionalUseCase;
import io.github.razuoss.farol_da_fe.infrastructure.adapter.in.web.dto.DevocionalRequest;
import io.github.razuoss.farol_da_fe.infrastructure.adapter.in.web.dto.DevocionalResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/devocional")
public class DevocionalController {

    private final DevocionalUseCase devocionalUseCase;

    public DevocionalController(DevocionalUseCase devocionalUseCase) {
        this.devocionalUseCase = devocionalUseCase;
    }

    @PostMapping
    public ResponseEntity<DevocionalResponse> gerar(@Valid @RequestBody DevocionalRequest request) {
        SolicitacaoDevocional solicitacao = new SolicitacaoDevocional(request.solicitacao());
        Devocional devocional = devocionalUseCase.gerarDevocional(solicitacao);
        
        DevocionalResponse response = new DevocionalResponse(
            devocional.titulo(),
            devocional.textoChave(),
            devocional.contextoHistorico(),
            devocional.analiseTexto(),
            devocional.aplicacaoPratica(),
            devocional.oracao(),
            devocional.avisoPastoral()
        );
        
        return ResponseEntity.ok(response);
    }
}
