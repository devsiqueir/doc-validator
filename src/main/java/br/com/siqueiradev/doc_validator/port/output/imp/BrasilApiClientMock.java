package br.com.siqueiradev.doc_validator.port.output.imp;

import br.com.siqueiradev.doc_validator.adapter.dto.response.CNPJResponseDTO;
import br.com.siqueiradev.doc_validator.adapter.dto.response.CPFResponseDTO;
import br.com.siqueiradev.doc_validator.port.output.IBrasilApiClient;
import org.springframework.stereotype.Component;

@Component
public class BrasilApiClientMock implements IBrasilApiClient {
    @Override
    public CNPJResponseDTO consultarDadosCnpj(String cnpj) {
        // Retorna um dado mock só para não quebrar
        return new CNPJResponseDTO(
                cnpj,
                "Empresa Teste",
                "Nome Fantasia",
                "ATIVA",
                "Comércio",
                "Sociedade Limitada",
                "2020-01-01",
                "Rua Teste, 123",
                "São Paulo",
                "SP"
        );
    }

    @Override
    public CPFResponseDTO consultarDadosCpf(String cpf) {
        return new CPFResponseDTO(
                cpf,
                "João da Silva",
                "1990-01-01",
                "Regular"
                // outros campos que existirem no seu record
        );
    }
}
