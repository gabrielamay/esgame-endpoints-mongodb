package br.com.fiap.esgames_endpoints.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "registro_atividade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RegistroAtividade {

    @Id
    private String id;

    private String usuarioId;
    private String missaoId;
    private String representanteId;
    private String materialId;

    private Integer quantidade;
    private Integer pontosGerados;
    private LocalDate dataRegistro;
}