package gp.moto.challenge_api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(name = "t_gpMottu_moto")
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMoto;

    @NotEmpty(message = "Valor inválido para status")
    @Length(max = 50, message = "Valor inválido para status. O valor deve ter no máximo 50 caracteres")
    private String status;

    @NotEmpty(message = "Valor inválido para as condições de manutenção")
    @Length(max = 200, message = "Valor inválido para as condições de manutenção. O valor deve ter no máximo 200 caracteres")
    private String condicoesManutencao;

    @NotEmpty(message = "Valor inválido para o identificador")
    @Length(max = 50, message = "Valor inválido para o identificador. O valor deve ter no máximo 50 caracteres")
    @Column(unique = true)
    private String identificador;

    @ManyToOne
    @JoinColumn(name = "id_tipo_moto")
    private TipoMoto idTipoMoto;

    @ManyToOne
    @JoinColumn(name = "id_secao")
    private SecaoFilial idSecaoFilial;

//    public Moto(MotoDTO motoDTO){
//        BeanUtils.copyProperties(motoDTO, this);
//    }


}
