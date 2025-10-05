package br.com.fiap.esgames_endpoints.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "usuarios_recompensas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UsuarioRecompensa {

    @Id
    private String id;

    private String usuarioId;
    private String recompensaId;
    private LocalDate dataResgate;
    private String statusResgate;
}
