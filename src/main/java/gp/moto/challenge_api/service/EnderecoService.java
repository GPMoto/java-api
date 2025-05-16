package gp.moto.challenge_api.service;

import gp.moto.challenge_api.dto.endereco.EnderecoDto;
import gp.moto.challenge_api.dto.endereco.EnderecoMapper;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Endereco;
import gp.moto.challenge_api.model.Pais;
import gp.moto.challenge_api.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;
    private final PaisService paisService;
    private final CidadeService cidadeService;
    private final EstadoService estadoService;

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    public Endereco findById(Long id) {
        return enderecoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
    }

    public Endereco save(EnderecoDto dto){
        log.info("olha o que tem no dto:{}", dto);
        Endereco endereco = enderecoMapper.toEntity(dto);
        log.info("conversao do mapper: {}", endereco);
        return enderecoRepository.save(endereco);
    }

    public Endereco saveOrFind(EnderecoDto dto) {

//        Pais pais = paisService.saveOrFindByName(strPais);
//        return enderecoRepository.save()
        // TODO: Realizar método para achar ou salvar endereco

        return null;
    }

    public Endereco update(Long id, EnderecoDto dto) {
        Endereco endereco = findById(id);
        enderecoMapper.updateEntityFromDto(dto, endereco);
        return enderecoRepository.save(endereco);
    }

    @Transactional
    public boolean delete(Long id) {
        Endereco endereco = findById(id);
        enderecoRepository.delete(endereco);
        return true;
    }
}
