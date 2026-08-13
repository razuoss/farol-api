package io.github.razuoss.farol_da_fe.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record DevocionalRequest(
    @NotBlank(message = "O tema não pode ser nulo ou vazio")
    String solicitacao
) {
}
