package br.com.fiap.esgames_endpoints.config.security;

import br.com.fiap.esgames_endpoints.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class VerificarToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        // ✅ 1. Verifica se o header existe e começa com "Bearer"
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.replace("Bearer ", "").trim();

            // ✅ 2. Valida o token e obtém o login (email)
            String login = tokenService.validarToken(token);

            if (login != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Optional<UserDetails> userDetailsOptional = usuarioRepository.findByEmail(login);

                // ✅ 3. Autentica se o usuário for encontrado
                if (userDetailsOptional.isPresent()) {
                    UserDetails userDetails = userDetailsOptional.get();

                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        // ✅ 4. Continua a cadeia de filtros
        filterChain.doFilter(request, response);
    }
}
