package br.com.siqueiradev.doc_validator.adapter.input;

import br.com.siqueiradev.doc_validator.adapter.dto.CNPJRequestDTO;
import br.com.siqueiradev.doc_validator.adapter.dto.CPFRequestDTO;
import br.com.siqueiradev.doc_validator.adapter.dto.response.CNPJResponseDTO;
import br.com.siqueiradev.doc_validator.adapter.dto.response.CPFResponseDTO;
import br.com.siqueiradev.doc_validator.port.input.IConsultarCnpj;
import br.com.siqueiradev.doc_validator.port.input.IConsultarCpf;
import br.com.siqueiradev.doc_validator.util.CnpjValidator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Consulta de CPF e CNPJ", description = "API para consulta pública de documentos")
public class CpfCnpjController {

    private final IConsultarCnpj consultarCnpj;
    private final IConsultarCpf consultarCpf;

    public CpfCnpjController(IConsultarCnpj consultarCnpj, IConsultarCpf consultarCpf) {
        this.consultarCnpj = consultarCnpj;
        this.consultarCpf = consultarCpf;
    }

    @GetMapping("/cnpj/consulta")
    public ResponseEntity<CNPJResponseDTO> consultarCnpj(@RequestParam String cnpj) {
        String cnpjNumerico = cnpj.replaceAll("[^\\d]", "");
        if (!CnpjValidator.isValid(cnpjNumerico)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CNPJ inválido.");
        }
        return ResponseEntity.ok(consultarCnpj.consultar(cnpjNumerico));
    }


    @PostMapping("/cpf/consulta")
    public ResponseEntity<CPFResponseDTO> consultar(@RequestBody @Valid CPFRequestDTO request) {
        return ResponseEntity.ok(consultarCpf.consultar(request.cpf()));
    }
}
