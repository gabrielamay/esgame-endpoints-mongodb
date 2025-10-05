package br.com.fiap.esgames_endpoints.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "representantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Representante {

    @Id
    private String id;
    private String nome;
    private String email;
    private String telefone;
    private LocalDateTime dataCadastro = LocalDateTime.now();
}