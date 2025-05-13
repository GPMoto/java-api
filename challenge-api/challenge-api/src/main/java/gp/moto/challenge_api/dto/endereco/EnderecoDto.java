package gp.moto.challenge_api.dto.endereco;

public record EnderecoDto  (String logradouro,
                            String numLogradouro,
                            String complemento,
                            String bairro,
                            String cidade,
                            String estado,
                            String pais) {
}
