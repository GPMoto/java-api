package gp.moto.challenge_api.model;


import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "id_endereco")
    private Endereco idEndereco;

    @OneToOne
    @JoinColumn(name = "id_contato")
    private Contato idContato;


}
