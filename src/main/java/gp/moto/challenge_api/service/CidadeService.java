package gp.moto.challenge_api.service;

import gp.moto.challenge_api.dto.endereco.cidade.CidadeDto;
import gp.moto.challenge_api.dto.endereco.cidade.CidadeMapper;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Cidade;
import gp.moto.challenge_api.model.Estado;
import gp.moto.challenge_api.repository.CidadeRepository;
import gp.moto.challenge_api.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CidadeService {

    private CidadeRepository cidadeRepository;
    private CidadeMapper cidadeMapper;

    public List<Cidade> findAll() {
        return cidadeRepository.findAll();
    }

    public Cidade findById(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cidade nção foi encontrada!"));
    }

    public Cidade save(CidadeDto dto) {
        return cidadeRepository.save(cidadeMapper.toEntity(dto));
    }


    public Cidade findOrSaveByName(CidadeDto dto){
    //        return cidadeRepository.findByName(dto.nome(), dto.idEstado()).orElse(save(new CidadeDto(dto)));
    // TODO: Realizar método para achar ou salvar cidade
    return null;
    }

    public boolean delete(Long id) {
        Cidade cidade = findById(id);
        cidadeRepository.delete(cidade);
        return true;
    }

    public Cidade update(Long id, CidadeDto dto) {
        Cidade cidade = findById(id);
        cidadeMapper.updateEntityFromDto(dto, cidade);
        return cidadeRepository.save(cidade);
    }
}
