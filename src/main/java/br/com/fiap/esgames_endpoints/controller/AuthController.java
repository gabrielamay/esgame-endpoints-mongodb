//package br.com.fiap.esgames_endpoints.controller;
//
//import br.com.fiap.esgames_endpoints.config.security.TokenService;
//import br.com.fiap.esgames_endpoints.dto.LoginDto;
//import br.com.fiap.esgames_endpoints.dto.TokenDto;
//import br.com.fiap.esgames_endpoints.dto.UsuarioCadastroDto;
//import br.com.fiap.esgames_endpoints.dto.UsuarioExibirDto;
//import br.com.fiap.esgames_endpoints.model.Usuario;
//import br.com.fiap.esgames_endpoints.service.UsuarioService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private TokenService tokenService;
//
//    @Autowired
//    private UsuarioService usuarioService;
//
//    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto) {
//
//        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
//                loginDto.email(),
//                loginDto.senha()
//        );
//
//        Authentication auth = authenticationManager.authenticate(usernamePassword);
//
//        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());
//
//        return ResponseEntity.ok(new TokenDto(token));
//    }
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/register")
//    public UsuarioExibirDto registrar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto) {
//        UsuarioExibirDto usuarioExibirDto = null;
//        usuarioExibirDto = usuarioService.gravar(usuarioCadastroDto);
//        return usuarioExibirDto;
//    }
//
//}
