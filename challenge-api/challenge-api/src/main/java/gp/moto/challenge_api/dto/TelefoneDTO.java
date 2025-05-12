package gp.moto.challenge_api.dto;

import gp.moto.challenge_api.model.Telefone;

public record TelefoneDTO(Long id_telefone, String ddi, String ddd, String numero) {

    public TelefoneDTO(Telefone telefone){
        this(
                telefone.getId_telefone(),
                telefone.getDdi(),
                telefone.getDdd(),
                telefone.getNumero()
        );
    }

}
