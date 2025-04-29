package br.com.siqueiradev.doc_validator.adapter.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

public record ResponseErrorDTO(
        String mensagem,
        int status,
        LocalDateTime timestamp,
        Map<String, String> detalhes // pode ser nulo se não for validação
) {
    public ResponseErrorDTO(String mensagem, int status, LocalDateTime timestamp) {
        this(mensagem, status, timestamp, null);
    }
}