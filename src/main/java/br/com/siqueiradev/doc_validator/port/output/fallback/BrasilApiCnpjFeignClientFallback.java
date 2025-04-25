package br.com.siqueiradev.doc_validator.port.output.fallback;

import br.com.siqueiradev.doc_validator.adapter.dto.response.BrasilApiCnpjResponse;
import br.com.siqueiradev.doc_validator.port.output.BrasilApiCnpjFeignClient;
import org.springframework.stereotype.Component;

@Component
public class BrasilApiCnpjFeignClientFallback implements BrasilApiCnpjFeignClient {
    @Override
    public BrasilApiCnpjResponse consultarCnpj(String cnpj) {
        // Pode retornar uma resposta default, ou lançar uma exceção customizada
        throw new RuntimeException("BrasilAPI está indisponível no momento.");
    }
}