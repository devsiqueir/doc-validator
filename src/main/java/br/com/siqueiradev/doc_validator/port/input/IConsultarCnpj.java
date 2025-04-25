package br.com.siqueiradev.doc_validator.port.input;


import br.com.siqueiradev.doc_validator.adapter.dto.response.CNPJResponseDTO;

public interface IConsultarCnpj {
    CNPJResponseDTO consultar(String cnpj);
}