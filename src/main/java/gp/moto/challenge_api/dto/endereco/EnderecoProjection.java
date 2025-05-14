package gp.moto.challenge_api.dto.endereco;

public record EnderecoProjection(Long idEndereco,
                                 String logradouro,
                                 String numero,
                                 String complemento,
                                 String cep,
                                 String cidade,
                                 String estado,
                                 String pais) {
}
