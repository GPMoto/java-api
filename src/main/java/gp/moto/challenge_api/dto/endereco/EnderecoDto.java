package gp.moto.challenge_api.dto.endereco;

public record EnderecoDto  (String logradouro,
                            String numLogradouro,
                            Long cidade,
                            String cep
                            ) {
}
