package gp.moto.challenge_api.dto.moto;

import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.model.TipoMoto;

public record MotoDTO(String status, String condicoesManutencao, String identificador,
                      Long idTipoMoto, Long idSecaoFilial) {

}
