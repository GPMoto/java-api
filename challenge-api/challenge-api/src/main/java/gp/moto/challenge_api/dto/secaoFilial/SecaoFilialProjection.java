package gp.moto.challenge_api.dto.secaoFilial;

public record SecaoFilialProjection (
        Long idSecao,
        Integer lado1,
        Integer lado2,
        Integer lado3,
        Integer lado4,
        Long idTipoSecao,
        Long idFilial
){
}
