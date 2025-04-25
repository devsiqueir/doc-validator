package br.com.siqueiradev.doc_validator.adapter.input;

import br.com.siqueiradev.doc_validator.adapter.dto.LoginRequestDTO;
import br.com.siqueiradev.doc_validator.adapter.dto.SignupRequestDTO;
import br.com.siqueiradev.doc_validator.domain.entity.Usuario;
import br.com.siqueiradev.doc_validator.domain.service.UsuarioService;
import br.com.siqueiradev.doc_validator.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequestDTO dto) {
        usuarioService.registrar(dto.nome(), dto.email(), dto.senha());
        return ResponseEntity.ok("Usuário registrado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto) {
        Optional<Usuario> userOpt = usuarioService.buscarPorEmail(dto.email());
        if (userOpt.isEmpty() || !passwordEncoder.matches(dto.senha(), userOpt.get().getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
        String token = jwtUtil.gerarToken(dto.email());
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}