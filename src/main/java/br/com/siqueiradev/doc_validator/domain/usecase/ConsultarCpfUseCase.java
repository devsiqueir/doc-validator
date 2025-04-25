package br.com.siqueiradev.doc_validator.domain.usecase;

import br.com.siqueiradev.doc_validator.adapter.dto.response.CPFResponseDTO;
import br.com.siqueiradev.doc_validator.port.input.IConsultarCpf;
import br.com.siqueiradev.doc_validator.port.output.IBrasilApiClient;
import org.springframework.stereotype.Service;

@Service
public class ConsultarCpfUseCase implements IConsultarCpf {
    private final IBrasilApiClient brasilApiClient;

    public ConsultarCpfUseCase(IBrasilApiClient brasilApiClient) {
        this.brasilApiClient = brasilApiClient;
    }

    @Override
    public CPFResponseDTO consultar(String cpf) {
        return brasilApiClient.consultarDadosCpf(cpf); // você terá que adicionar esse método na interface IBrasilApiClient
    }
}