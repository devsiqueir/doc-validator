package br.com.siqueiradev.doc_validator.domain.service;

import br.com.siqueiradev.doc_validator.adapter.output.UsuarioRepositoryJpa;
import br.com.siqueiradev.doc_validator.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepositoryJpa usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrar(String nome, String email, String senha) {
        String senhaHash = passwordEncoder.encode(senha);
        Usuario user = new Usuario(nome, email, senhaHash);
        return usuarioRepository.save(user);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
