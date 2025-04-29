package br.com.siqueiradev.doc_validator.adapter.input;

import br.com.siqueiradev.doc_validator.adapter.dto.LoginRequestDTO;
import br.com.siqueiradev.doc_validator.adapter.dto.ResponseSuccessDTO;
import br.com.siqueiradev.doc_validator.adapter.dto.SignupRequestDTO;
import br.com.siqueiradev.doc_validator.adapter.dto.response.UsuarioMeResponseDTO;
import br.com.siqueiradev.doc_validator.config.UserPrincipal;
import br.com.siqueiradev.doc_validator.domain.entity.Usuario;
import br.com.siqueiradev.doc_validator.domain.service.UsuarioService;
import br.com.siqueiradev.doc_validator.util.JwtUtil;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
@Hidden
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioService usuarioService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseSuccessDTO> register(@RequestBody @Valid SignupRequestDTO dto) {
        usuarioService.registrar(dto.nome(), dto.email(), dto.senha());
        ResponseSuccessDTO response = new ResponseSuccessDTO(
                "Usuário registrado com sucesso",
                HttpStatus.CREATED.value(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO dto) {
        Optional<Usuario> userOpt = usuarioService.buscarPorEmail(dto.email());
        if (userOpt.isEmpty() || !passwordEncoder.matches(dto.senha(), userOpt.get().getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("erro", "Credenciais inválidas"));
        }
        String token = jwtUtil.gerarToken(dto.email());
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioMeResponseDTO> getMe(@AuthenticationPrincipal UserPrincipal user) {
        Usuario usuario = user.getUsuario();
        return ResponseEntity.ok(new UsuarioMeResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        ));
    }
}
