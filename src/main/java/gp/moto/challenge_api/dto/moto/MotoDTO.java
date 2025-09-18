package gp.moto.challenge_api.dto.moto;

import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.model.TipoMoto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MotoDTO(
    @NotEmpty(message = "Status é obrigatório")
    @Size(max = 50, message = "Status deve ter no máximo 50 caracteres")
    String status, 
    
    @NotEmpty(message = "Condições de manutenção são obrigatórias")
    @Size(max = 200, message = "Condições de manutenção devem ter no máximo 200 caracteres")
    String condicoesManutencao, 
    
    @NotEmpty(message = "Identificador é obrigatório")
    @Size(min = 3, max = 20, message = "Identificador deve ter entre 3 e 20 caracteres")
    String identificador,
    
    @NotNull(message = "Tipo da moto é obrigatório")
    Long idTipoMoto, 
    
    @NotNull(message = "Seção/Filial é obrigatória")
    Long idSecaoFilial
) {

}