package br.com.siqueiradev.doc_validator.adapter.dto.response;

public record BrasilApiCnpjResponse(
        String cnpj,
        String razao_social,
        String nome_fantasia,
        String descricao_situacao_cadastral,
        String cnae_fiscal_descricao,
        String natureza_juridica,
        String data_abertura,
        String logradouro,
        String numero,
        String municipio,
        String uf
) {}
