package br.com.fiap.esgames_endpoints.service;

import br.com.fiap.esgames_endpoints.dto.UsuarioCadastroDto;
import br.com.fiap.esgames_endpoints.dto.UsuarioExibirDto;
import br.com.fiap.esgames_endpoints.exception.UsuarioNaoEncontradoException;
import br.com.fiap.esgames_endpoints.model.Usuario;
import br.com.fiap.esgames_endpoints.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ✅ Cadastrar novo usuário
    public UsuarioExibirDto gravar(UsuarioCadastroDto usuarioCadastroDto) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioCadastroDto.senha());
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDto, usuario);
        usuario.setSenha(senhaCriptografada);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioExibirDto(usuarioSalvo);
    }

    // ✅ Listar todos os usuários (paginação)
    public Page<UsuarioExibirDto> listarTodosUsuarios(Pageable paginacao) {
        return usuarioRepository.findAll(paginacao).map(UsuarioExibirDto::new);
    }

    // ✅ Buscar por nome
    public UsuarioExibirDto buscarPorNome(String nome) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNome(nome);

        return usuarioOptional
                .map(UsuarioExibirDto::new)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado."));
    }

    // ✅ Buscar por ID (tipo String no MongoDB)
    public UsuarioExibirDto buscarPorId(String id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        return usuarioOptional
                .map(UsuarioExibirDto::new)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado."));
    }
}