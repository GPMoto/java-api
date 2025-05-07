package gp.moto.challenge_api.dto;

import gp.moto.challenge_api.model.Filial;
import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.model.TipoMoto;

public record MotoDTO(Long idMoto, String condicoes, String condicoesManutencao, String identificador,
                      TipoMoto idTipoMoto, Filial idFilial) {

    public MotoDTO(Moto moto){
        this(
                moto.getIdMoto(),
                moto.getCondicoes(),
                moto.getCondicoesManutencao(),
                moto.getIdentificador(),
                moto.getIdTipoMoto(),
                moto.getIdFilial()
        );
    }

}
