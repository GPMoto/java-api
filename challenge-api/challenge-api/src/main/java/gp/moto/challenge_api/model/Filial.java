package gp.moto.challenge_api.model;


import gp.moto.challenge_api.dto.FilialDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

@Data
@Entity
@Table(name = "t_gpMottu_filial")
@NoArgsConstructor
@AllArgsConstructor
public class Filial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFilial;

    @NotEmpty(message = "Valor inválido para cnpj da filial")
    @Length(max = 14, message = "Valor inválido para nome da filial. O valor deve ter no máximo 14 caracteres")
    @Column(unique = true)
    private String cnpjFilial;

    @NotEmpty(message = "Valor inválido para senha da filial")
    @Length(max = 200, message = "Valor inválido para nome da filial. O valor deve ter no máximo 200 caracteres")
    private String senhaFilial;

    @OneToOne
    @JoinColumn(name = "id_pais")
    private Endereco idEndereco;

    @OneToOne
    @JoinColumn(name = "id_contato")
    private Contato idContato;

    public Filial(FilialDTO filialDTO){
        BeanUtils.copyProperties(filialDTO, this);
    }

}
