package gp.moto.challenge_api.dto.secaoFilial;

public record SecaoFilialDto (
        Double lado1,
        Double lado2,
        Double lado3,
        Double lado4,
        Long idTipoSecao,
        Long idFilial
) {
}
