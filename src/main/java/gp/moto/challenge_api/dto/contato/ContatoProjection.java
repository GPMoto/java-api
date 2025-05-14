package gp.moto.challenge_api.dto.contato;

import gp.moto.challenge_api.model.Telefone;

public record ContatoProjection (String nmDono, Integer status, Telefone idTelefone) {
}
