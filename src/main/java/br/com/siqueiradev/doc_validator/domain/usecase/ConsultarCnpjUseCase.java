package br.com.siqueiradev.doc_validator.domain.usecase;

import br.com.siqueiradev.doc_validator.adapter.dto.response.CNPJResponseDTO;
import br.com.siqueiradev.doc_validator.domain.entity.ConsultaLog;
import br.com.siqueiradev.doc_validator.domain.repository.ConsultaLogRepository;
import br.com.siqueiradev.doc_validator.port.input.IConsultarCnpj;
import br.com.siqueiradev.doc_validator.port.output.IBrasilApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConsultarCnpjUseCase implements IConsultarCnpj {

    private final IBrasilApiClient brasilApiClient;
    private final ConsultaLogRepository consultaLogRepository;
    private final ObjectMapper objectMapper; // Injetado pelo Spring

    @Autowired
    public ConsultarCnpjUseCase(
            IBrasilApiClient brasilApiClient,
            ConsultaLogRepository consultaLogRepository,
            ObjectMapper objectMapper) {
        this.brasilApiClient = brasilApiClient;
        this.consultaLogRepository = consultaLogRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public CNPJResponseDTO consultar(String cnpj) {
        CNPJResponseDTO response = brasilApiClient.consultarDadosCnpj(cnpj);

        String resultadoJson;
        try {
            resultadoJson = objectMapper.writeValueAsString(response);
        } catch (Exception e) {
            resultadoJson = response.toString();
        }

        ConsultaLog log = new ConsultaLog(
                "CNPJ",
                cnpj,
                resultadoJson,
                LocalDateTime.now()
        );
        consultaLogRepository.save(log);

        return response;
    }
}
