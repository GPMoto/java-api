package gp.moto.challenge_api.dto.endereco;

public record EnderecoDto  (String logradouro,
                            String numLogradouro,
                            String cidade,
                            String estado,
                            String pais,
                            String cep
                            ) {
}
