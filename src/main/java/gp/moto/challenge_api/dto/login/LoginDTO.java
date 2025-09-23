package gp.moto.challenge_api.dto.login;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {

    @NotEmpty(message = "Nome de usuário é obrigatório")
    @Size(min = 3, max = 50, message = "Nome de usuário deve ter entre 3 e 50 caracteres")
    private String username;

    @NotEmpty(message = "Senha é obrigatória")
    @Size(min = 6, max = 200, message = "Senha deve ter entre 6 e 200 caracteres")
    private String password;
}
