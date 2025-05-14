package gp.moto.challenge_api.dto.telefone;

import gp.moto.challenge_api.model.Telefone;

public record TelefoneProjection(Long id_telefone, String ddi, String ddd, String numero) {

}
