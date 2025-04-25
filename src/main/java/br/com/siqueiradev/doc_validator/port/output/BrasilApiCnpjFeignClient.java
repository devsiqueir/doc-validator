package br.com.siqueiradev.doc_validator.port.output;

import br.com.siqueiradev.doc_validator.adapter.dto.response.BrasilApiCnpjResponse;
import br.com.siqueiradev.doc_validator.port.output.fallback.BrasilApiCnpjFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "brasilapi-cnpj",
        url = "${brasilapi.url}",
        fallback = BrasilApiCnpjFeignClientFallback.class
)
public interface BrasilApiCnpjFeignClient {
    @GetMapping("/api/cnpj/v1/{cnpj}")
    BrasilApiCnpjResponse consultarCnpj(@PathVariable("cnpj") String cnpj);
}
