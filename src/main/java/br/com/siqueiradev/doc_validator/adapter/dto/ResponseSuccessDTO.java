package br.com.siqueiradev.doc_validator.adapter.dto;

import java.time.LocalDateTime;

public record ResponseSuccessDTO(
        String mensagem,
        int status,
        LocalDateTime timestamp
) {}