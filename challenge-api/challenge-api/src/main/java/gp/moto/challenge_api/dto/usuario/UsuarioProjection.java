package gp.moto.challenge_api.dto.usuario;

public record UsuarioProjection(
        Long idUsuario,
        String nome,
        String email,
        Long idFilial,
        Long idPerfil
) {
}
