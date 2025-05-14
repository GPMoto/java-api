package gp.moto.challenge_api.dto.usuario;

public record UsuarioWithPasswordProjection(
        Long idUsuario,
        String nome,
        String email,
        String senha,
        Long idFilial,
        Long idPerfil
) {
}
