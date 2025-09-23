package gp.moto.challenge_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(name = "t_gpMottu_qrcode")

public class Qrcode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQrcode;

    @OneToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_moto")
    private Moto idMoto;


    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_filial")
    private Filial filial;

    @NotEmpty(message = "Valor inválido para o valor Uwb")
    @Length(max = 200, message = "Valor inválido para o valor Uwb. O valor deve ter no máximo 200 caracteres")
    private String vlQrcode;


}
