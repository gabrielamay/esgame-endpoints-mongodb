package br.com.fiap.esgames_endpoints.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Usuario {

    @Id
    private String id;

    private String nome;
    private String email;
    private String senha;
    private String setor;
    private UsuarioRole role;

    // ✅ Métodos obrigatórios que o DTO precisa
    public String getId() { return id; }

    public String getNome() { return nome; }

    public String getEmail() { return email; }

    public UsuarioRole getRole() { return role; }

    public String getSetor() { return setor; }

    public void setSenha(String senhaCriptografada) {
        this.senha = senhaCriptografada;
    }
}