package io.github.razuoss.farol_da_fe.infrastructure.adapter.in.web.dto;

public record DevocionalResponse(
    String titulo,
    String textoChave,
    String contextoHistorico,
    String analiseTexto,
    String aplicacaoPratica,
    String oracao,
    String avisoPastoral
) {
}
