package br.com.siqueiradev.doc_validator.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ConsultaLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoConsulta; // "CNPJ" ou "CPF"
    private String documento;
    @Column(columnDefinition = "text")// CNPJ ou CPF consultado
    private String resultado;    // JSON, ou resumo, ou status

    private LocalDateTime dataConsulta;

    // Construtores, getters/setters...
    public ConsultaLog() {}

    public ConsultaLog(String tipoConsulta, String documento, String resultado, LocalDateTime dataConsulta) {
        this.tipoConsulta = tipoConsulta;
        this.documento = documento;
        this.resultado = resultado;
        this.dataConsulta = dataConsulta;
    }

    // getters e setters aqui
}