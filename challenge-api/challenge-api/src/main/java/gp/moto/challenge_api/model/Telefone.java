package gp.moto.challenge_api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(name = "t_gpMottu_telefone")
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_telefone;

    @NotEmpty(message = "Valor inválido para ddi")
    @Length(max = 3, message = "Valor inválido para ddi. O valor deve ter no máximo 3 caracteres")
    private String ddi;

    @NotEmpty(message = "Valor inválido para ddd")
    @Length(max = 3, message = "Valor inválido para ddd. O valor deve ter no máximo 3 caracteres")
    private String ddd;

    @NotEmpty(message = "Valor inválido para número")
    @Length(max = 10, message = "Valor inválido para número. O valor deve ter no máximo 10 caracteres")
    private String numero;
}
