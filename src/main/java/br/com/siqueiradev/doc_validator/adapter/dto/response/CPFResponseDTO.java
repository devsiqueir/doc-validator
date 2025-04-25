package br.com.siqueiradev.doc_validator.adapter.dto.response;

public record CPFResponseDTO(
        String cpf,
        String nomeCompleto,
        String dataNascimento,
        String situacaoCadastral
        // outros campos desejados
) {}