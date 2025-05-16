package gp.moto.challenge_api.dto.endereco;

public record EnderecoDto  (String logradouro,
                            Integer numLogradouro,
                            Long cidade,
                            String cep
                            ) {
}
