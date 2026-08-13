package io.github.razuoss.farol_da_fe.domain.devocional.service;

import io.github.razuoss.farol_da_fe.domain.devocional.model.Devocional;
import io.github.razuoss.farol_da_fe.domain.devocional.model.SolicitacaoDevocional;
import io.github.razuoss.farol_da_fe.domain.shared.port.out.GenAiPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DevocionalServiceTest {

    @Mock
    private GenAiPort genAiPort;

    private DevocionalService service;

    @BeforeEach
    void setUp() {
        service = new DevocionalService(genAiPort);
    }

    @Test
    void dadoTemaValido_quandoGerar_entaoRetornaDevocional() {
        // Dado
        SolicitacaoDevocional solicitacao = new SolicitacaoDevocional("Esperança");
        Devocional devocionalEsperado = new Devocional("T", "TC", "CH", "AT", "AP", "O", "APast");
        when(genAiPort.gerar(any())).thenReturn(devocionalEsperado);

        // Quando
        Devocional resultado = service.gerarDevocional(solicitacao);

        // Então
        assertThat(resultado).isNotNull();
        assertThat(resultado.titulo()).isEqualTo("T");
    }

    @Test
    void dadoTemaNulo_quandoGerar_entaoLancaExcecao() {
        // Dado
        SolicitacaoDevocional solicitacao = new SolicitacaoDevocional(null);

        // Quando / Então
        assertThatThrownBy(() -> service.gerarDevocional(solicitacao))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
