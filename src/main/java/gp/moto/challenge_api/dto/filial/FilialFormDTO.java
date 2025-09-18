package gp.moto.challenge_api.dto.filial;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record FilialFormDTO(
    Long idFilial,
    
    @NotEmpty(message = "CNPJ é obrigatório")
    @Length(max = 14, message = "CNPJ deve ter no máximo 14 caracteres")
    String cnpjFilial,
    
    @NotEmpty(message = "Senha é obrigatória")
    @Length(max = 200, message = "Senha deve ter no máximo 200 caracteres")
    String senhaFilial,

    Long idEndereco,
    Long idContato,
    

    @NotEmpty(message = "CEP é obrigatório")
    @Length(min=8, max = 8, message = "CEP deve ter no máximo 8 caracteres")
    String cep,
    
    @NotEmpty(message = "Logradouro é obrigatório")
    @Length(max = 200, message = "Logradouro deve ter no máximo 200 caracteres")
    String nmLogradouro,
    
    @NotNull(message = "Número do logradouro é obrigatório")
    Integer nrLogradouro,
    
    @NotNull(message = "Cidade é obrigatória")
    Long idCidade,
    

    @NotEmpty(message = "Nome do responsável é obrigatório")
    @Length(max = 200, message = "Nome deve ter no máximo 200 caracteres")
    String nmDono,
    
    @NotNull(message = "Status do contato é obrigatório")
    Integer status,
    
    @NotEmpty(message = "DDD é obrigatório")
    @Length(min=2, max = 2, message = "DDD deve ter no máximo 2 caracteres")
    String ddd,
    
    @NotEmpty(message = "Número do telefone é obrigatório")
    @Length(max = 15, message = "Telefone deve ter no máximo 15 caracteres")
    String numero
) {
}