package gp.moto.challenge_api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(name = "t_gpMottu_tipo_moto")
public class TipoMoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_tipo_moto;
    @NotEmpty(message = "Valor inválido para tipo de moto")
    @Length( max = 40 , message = "Valor inválido para tipo de moto. O valor deve ter no máximo 40 caracteres")
    private String nmTipo;
}
