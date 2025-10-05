package br.com.fiap.esgames_endpoints.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "materiais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Material {

    @Id
    private String id;
    private String nome;
    private String unidade;
    private Integer pontosPorUnidade;
}
