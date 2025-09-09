package gp.moto.challenge_api.dto.qrcode;

import gp.moto.challenge_api.dto.filial.FilialProjection;
import gp.moto.challenge_api.dto.moto.MotoProjection;

public record QrcodeProjection(Long idQrcode, String vlQrcode, MotoProjection moto, FilialProjection filial) {
}
