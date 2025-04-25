package br.com.siqueiradev.doc_validator.port.input;

import br.com.siqueiradev.doc_validator.adapter.dto.response.CPFResponseDTO;

public interface IConsultarCpf {
    CPFResponseDTO consultar(String cpf);
}