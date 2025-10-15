//package br.com.fiap.esgames_endpoints.service;
//
//import br.com.fiap.esgames_endpoints.repository.UsuarioRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthorizationService implements UserDetailsService {
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return usuarioRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + username));
//    }
//}