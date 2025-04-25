package br.com.siqueiradev.doc_validator.adapter.output;

import br.com.siqueiradev.doc_validator.domain.entity.Usuario;
import br.com.siqueiradev.doc_validator.port.output.IUsuarioRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositoryJpa extends JpaRepository<Usuario, Long>, IUsuarioRepository {
}