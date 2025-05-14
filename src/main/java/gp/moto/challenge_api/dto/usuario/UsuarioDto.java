package gp.moto.challenge_api.dto.usuario;

public record UsuarioDto (
        String nome,
        String email,
        String senha,
        Long idFilial,
        Long idPerfil
) {
}
