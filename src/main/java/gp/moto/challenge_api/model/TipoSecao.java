package gp.moto.challenge_api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(name = "t_gpMottu_tipo_secao")
public class TipoSecao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoSecao;
    @NotEmpty(message = "Valor inválido para tipo de seção")
    @Length(max=200, message = "Valor inválido para tipo de seção. O valor deve ter no máximo 200 caracteres")
    private String nmSecao;
}
