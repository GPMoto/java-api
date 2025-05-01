package gp.moto.challenge_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "t_gpMottu_secoes_filial")
public class SecoesFilial {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSecao;

    @NotNull(message = "Valor inválido para o lado 1")
    private double lado1;
    @NotNull(message = "Valor inválido para o lado 2")
    private double lado2;
    @NotNull(message = "Valor inválido para o lado 3")
    private double lado3;
    @NotNull(message = "Valor inválido para o lado 4")
    private double lado4;

    // Não sei se ta certo, Many To One olhar isso
    @ManyToOne
    @JoinColumn(name = "id_tipo_secao")
    private TipoSecao idTipoSecao;

    // Não sei se ta certo, Many To One olhar isso
    @ManyToOne
    @JoinColumn(name = "id_filial")
    private Filial idFilial;
}
