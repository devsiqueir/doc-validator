package br.com.siqueiradev.doc_validator.adapter.input;

import br.com.siqueiradev.doc_validator.adapter.dto.response.UsuarioResponseDTO;
import br.com.siqueiradev.doc_validator.config.UserPrincipal;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@Hidden
@RestController
@RequestMapping("/api/v1/user")
@SecurityRequirement(name = "bearer-key") // Mostra no Swagger que precisa de JWT
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> getLoggedUser(@AuthenticationPrincipal UserPrincipal principal) {
        UsuarioResponseDTO response = new UsuarioResponseDTO(
                principal.getId(),
                principal.getNome(),
                principal.getEmail()
        );
        return ResponseEntity.ok(response);
    }
}
