package gp.moto.challenge_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(name = "t_gpMottu_uwb")
public class Uwb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUwb;

    @OneToOne
    @JoinColumn(name = "id_moto")
    private Moto idMoto;

    @NotEmpty(message = "Valor inválido para o valor Uwb")
    @Length(max = 200, message = "Valor inválido para o valor Uwb. O valor deve ter no máximo 200 caracteres")
    private String vlUwb;

    public Uwb(Moto idMoto, String vlUwb) {
        this.idMoto = idMoto;
        this.vlUwb = vlUwb;
    }


}
