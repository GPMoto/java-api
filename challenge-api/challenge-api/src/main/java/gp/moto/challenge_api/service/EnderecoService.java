package gp.moto.challenge_api.service;

import gp.moto.challenge_api.dto.endereco.EnderecoDto;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Endereco;
import gp.moto.challenge_api.model.Pais;
import gp.moto.challenge_api.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final PaisService paisService;
    private final CidadeService cidadeService;
    private final EstadoService estadoService;

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    public Endereco findById(Long id) {
        return enderecoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
    }

    public Endereco saveOrFind(EnderecoDto dto) {
        String strPais = dto.pais();
        String strEstado = dto.estado();
        String strCidade = dto.cidade();

//        Pais pais = paisService.saveOrFindByName(strPais);
//        return enderecoRepository.save()
        // TODO: Realizar método para achar ou salvar endereco

        return null;
    }
}
