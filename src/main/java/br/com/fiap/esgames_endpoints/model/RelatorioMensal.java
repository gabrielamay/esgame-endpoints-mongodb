package br.com.fiap.esgames_endpoints.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "relatorios_mensais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RelatorioMensal {

    @Id
    private String id;

    private LocalDate mesReferencia;
    private LocalDate anoReferencia;
    private Integer totalPontos;
    private Integer totalDoacoes;
    private Integer totalReciclados;
    private String usuarioId;
}