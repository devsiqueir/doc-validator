package br.com.siqueiradev.doc_validator.domain.exception;

public record ErrorResponseDTO(
        String mensagem,
        int status,
        String timestamp
) {}
