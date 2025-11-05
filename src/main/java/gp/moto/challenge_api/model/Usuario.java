package gp.moto.challenge_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString(exclude = "expoPushTokenUsers")
@EqualsAndHashCode(exclude = "expoPushTokenUsers")
@Entity
@Table(name = "t_gpMottu_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotEmpty(message = "Valor inválido para nome do usuário")
    @Length(
        max = 200,
        message = "Valor inválido para nome do usuário. O valor deve ter no máximo 200 caracteres"
    )
    private String nmUsuario;

    @NotEmpty(message = "Valor inválido para email")
    @Length(
        max = 200,
        message = "Valor inválido para email. O valor deve ter no máximo 200 caracteres"
    )
    @Column(unique = true)
    private String nmEmail;

    @NotEmpty(message = "Valor inválido para senha")
    @Length(
        max = 200,
        message = "Valor inválido para senha. O valor deve ter no máximo 200 caracteres"
    )
    private String senha;

    @ManyToOne
    @JoinColumn(name = "id_filial")
    private Filial idFilial;

    @ManyToOne
    @JoinColumn(name = "id_perfil")
    private Perfil idPerfil;

    @OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
    @Nullable
    private List<PushToken> pushTokens;

    @Enumerated(EnumType.STRING)
    @Column(name = "idiom", nullable = true)
    private LanguageEnumPreferences languageEnumPreference;
}
