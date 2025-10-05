package br.com.fiap.esgames_endpoints.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recompensas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Recompensa {

    @Id
    private String id;
    private String nome;
    private String descricao;
    private Integer pontosNecessarios;
    private Integer estoque;
    private String imagem;
}
