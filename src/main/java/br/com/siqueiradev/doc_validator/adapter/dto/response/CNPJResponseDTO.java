package br.com.siqueiradev.doc_validator.adapter.dto.response;

public record CNPJResponseDTO(
        String cnpj,
        String razaoSocial,
        String nomeFantasia,
        String situacaoCadastral,
        String atividadePrincipal,
        String naturezaJuridica,
        String dataAbertura,
        String endereco,
        String municipio,
        String uf
) {}

