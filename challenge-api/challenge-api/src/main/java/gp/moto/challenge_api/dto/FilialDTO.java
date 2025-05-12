package gp.moto.challenge_api.dto;

import gp.moto.challenge_api.model.Contato;
import gp.moto.challenge_api.model.Endereco;
import gp.moto.challenge_api.model.Filial;

public record FilialDTO(Long idFilial, String cnpjFilial, String senhaFilial, Long idEndereco, Long idContato) {


    public FilialDTO(Filial filial){
        this(
                filial.getIdFilial(),
                filial.getCnpjFilial(),
                filial.getSenhaFilial(),
                filial.getIdEndereco().getIdEndereco(),
                filial.getIdContato().getIdContato()
        );
    }

}
