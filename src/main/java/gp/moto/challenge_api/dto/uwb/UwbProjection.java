package gp.moto.challenge_api.dto.uwb;

import gp.moto.challenge_api.dto.moto.MotoProjection;

public record UwbProjection (Long idUwb, String vlUwb, MotoProjection moto) {
}
