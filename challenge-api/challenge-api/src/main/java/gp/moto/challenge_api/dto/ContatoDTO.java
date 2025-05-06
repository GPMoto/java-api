package gp.moto.challenge_api.dto;

import gp.moto.challenge_api.model.Contato;
import gp.moto.challenge_api.model.Telefone;

public record ContatoDTO(Long idContato, String nmDono, int status, String nmEmail, Telefone idTelefone) {

    public ContatoDTO(Contato contato){
        this(
                contato.getIdContato(),
                contato.getNmDono(),
                contato.getStatus(),
                contato.getNmEmail(),
                contato.getIdTelefone()
        );
    }


}
