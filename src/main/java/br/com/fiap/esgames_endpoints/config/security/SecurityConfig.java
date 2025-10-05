package br.com.fiap.esgames_endpoints.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private VerificarToken verificarToken;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/usuario").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/ranking/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/selos/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/selos").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/missoes").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/missoes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/ranking/registro-atividade").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/selos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/missoes/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/selos/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/selos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/missoes/{id}").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated()
                )
                .addFilterBefore(
                        verificarToken,
                        UsernamePasswordAuthenticationFilter.class
                );
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
