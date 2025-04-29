package br.com.siqueiradev.doc_validator.config;

import br.com.siqueiradev.doc_validator.domain.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {

    private final Usuario usuario;

    public UserPrincipal(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return usuario.getId();
    }

    public String getNome() {
        return usuario.getNome();
    }

    public String getEmail() {
        return usuario.getEmail();
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // ajuste se tiver roles
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail(); // necessário para Spring Security
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
