package br.com.siqueiradev.doc_validator.adapter.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequestDTO(
        @NotBlank(message = "Nome é obrigatório.")
        String nome,

        @NotBlank(message = "Email é obrigatório.")
        @Email(message = "Email inválido.")
        String email,

        @NotBlank(message = "Senha é obrigatória.")
        @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres.")
        String senha
) {}