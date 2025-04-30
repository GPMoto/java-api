package gp.moto.challenge_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
@Entity
@Table(name = "t_gpMottu_pais")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPais;
    @NotEmpty(message = "Valor inválido para o país")
    @Length(max = 100, message = "Valor inválido para o país. O valor deve ter no máximo 100 caracteres")
    private String nmPais;
}
