package br.com.siqueiradev.doc_validator.port.output;

import br.com.siqueiradev.doc_validator.adapter.dto.response.CNPJResponseDTO;
import br.com.siqueiradev.doc_validator.adapter.dto.response.CPFResponseDTO;

public interface IBrasilApiClient {
    CNPJResponseDTO consultarDadosCnpj(String cnpj);
    CPFResponseDTO consultarDadosCpf(String cpf);
}
