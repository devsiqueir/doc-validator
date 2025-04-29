package br.com.siqueiradev.doc_validator.config;

import br.com.siqueiradev.doc_validator.domain.service.UsuarioService;
import br.com.siqueiradev.doc_validator.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthFilter implements Filter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String authHeader = req.getHeader("Authorization");

        // Já existe autenticação? Ignora
        if (SecurityContextHolder.getContext().getAuthentication() == null &&
                authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.replace("Bearer ", "");

            if (jwtUtil.validarToken(token)) {
                String email = jwtUtil.extrairEmail(token);
                usuarioService.buscarPorEmail(email).ifPresent(usuario -> {
                    UserPrincipal principal = new UserPrincipal(usuario);
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                });
            } else {
                ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
