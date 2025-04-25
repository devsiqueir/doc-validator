package br.com.siqueiradev.doc_validator.adapter.output;

import br.com.siqueiradev.doc_validator.adapter.dto.response.BrasilApiCnpjResponse;
import br.com.siqueiradev.doc_validator.adapter.dto.response.CNPJResponseDTO;
import br.com.siqueiradev.doc_validator.adapter.dto.response.CPFResponseDTO;
import br.com.siqueiradev.doc_validator.port.output.BrasilApiCnpjFeignClient;
import br.com.siqueiradev.doc_validator.port.output.IBrasilApiClient;
import org.springframework.stereotype.Component;

@Component
public class BrasilApiClient implements IBrasilApiClient {
    private final BrasilApiCnpjFeignClient feignClient;

    public BrasilApiClient(BrasilApiCnpjFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @Override
    public CNPJResponseDTO consultarDadosCnpj(String cnpj) {
        BrasilApiCnpjResponse apiResponse = feignClient.consultarCnpj(cnpj);
        if (apiResponse == null) throw new RuntimeException("CNPJ não encontrado.");

        return new CNPJResponseDTO(
                apiResponse.cnpj(),
                apiResponse.razao_social(),
                apiResponse.nome_fantasia(),
                apiResponse.descricao_situacao_cadastral(),
                apiResponse.cnae_fiscal_descricao(),
                apiResponse.natureza_juridica(),
                apiResponse.data_abertura(),
                apiResponse.logradouro() + ", " + apiResponse.numero(),
                apiResponse.municipio(),
                apiResponse.uf()
        );
    }
    @Override
    public CPFResponseDTO consultarDadosCpf(String cpf) {
        // Mock para CPF (até ter API real)
        return new CPFResponseDTO(
                cpf,
                "João da Silva",
                "1990-01-01",
                "Regular"
        );
    }
}