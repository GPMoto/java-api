package gp.moto.challenge_api.dto;

import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.model.Uwb;

public record UwbDTO(Long idUwb, Moto idMoto, String vlUwb) {

    public UwbDTO(Uwb uwb){
        this(
                uwb.getIdUwb(),
                uwb.getIdMoto(),
                uwb.getVlUwb()
        );
    }
}
