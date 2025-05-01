package gp.moto.challenge_api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(name = "t_gpMottu_perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerfil;

    @NotEmpty(message = "Valor inválido para tipo de perfil")
    @Length(max = 100, message = "Valor inválido para tipo de perfil. O valor deve ter no máximo 100 caracteres")
    private String nmPerfil;


}
