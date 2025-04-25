package br.com.siqueiradev.doc_validator.port.output;


import br.com.siqueiradev.doc_validator.domain.entity.Usuario;

import java.util.Optional;

public interface IUsuarioRepository {
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario usuario);
}