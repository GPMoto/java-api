package gp.moto.challenge_api.dto.moto;

import gp.moto.challenge_api.model.Filial;
import gp.moto.challenge_api.model.TipoMoto;

public record MotoProjection(Long idMoto, String status, String condicoesManutencao, String identificador,
                             TipoMoto idTipoMoto, Long idSecaoFilial) {
}
