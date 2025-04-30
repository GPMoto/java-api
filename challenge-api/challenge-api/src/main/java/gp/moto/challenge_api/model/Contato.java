package gp.moto.challenge_api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(name = "t_gpMottu_contato")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContato;

    @NotEmpty(message = "Valor inválido para nome do contato")
    @Length(max = 200, message = "Valor inválido para nome do contato. O valor deve ter no máximo 200 caracteres")
    private String nmDono;

    @NotNull(message = "Valor inválido para status")
    @Length(max = 1, message = "Valor inválido para status. O valor deve ter no máximo 1 caracteres")
    private int status;

    @NotEmpty(message = "Valor inválido para email")
    @Length(max = 200, message = "Valor inválido para email. O valor deve ter no máximo 200 caracteres")
    @Email(message = "Valor inválido para email")
    private String nmEmail;

    @OneToOne
    @JoinColumn(name = "id_telefone")
    private Telefone idTelefone;
}
