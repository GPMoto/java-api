package gp.moto.challenge_api.dto.endereco;

public record EnderecoProjection(Long idEndereco,
                                 String logradouro,
                                 Integer numero,
                                 String complemento,
                                 String cep,
                                 String cidade,
                                 String estado,
                                 String pais) {
}
