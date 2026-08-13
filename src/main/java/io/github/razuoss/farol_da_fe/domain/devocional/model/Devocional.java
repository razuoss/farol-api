package io.github.razuoss.farol_da_fe.domain.devocional.model;

public record Devocional(
    String titulo,
    String textoChave,
    String contextoHistorico,
    String analiseTexto,
    String aplicacaoPratica,
    String oracao,
    String avisoPastoral
) {
}
