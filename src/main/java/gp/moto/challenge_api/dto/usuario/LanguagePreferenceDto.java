package gp.moto.challenge_api.dto.usuario;

import gp.moto.challenge_api.model.LanguageEnumPreferences;
import lombok.Data;

@Data
public class LanguagePreferenceDto {
    private LanguageEnumPreferences language;
}