package gp.moto.challenge_api.service;

import gp.moto.challenge_api.dto.endereco.pais.PaisDto;
import gp.moto.challenge_api.dto.endereco.pais.PaisMapper;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Pais;
import gp.moto.challenge_api.repository.PaisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaisService {

    private final PaisRepository paisRepository;
    private final PaisMapper paisMapper;
    public List<Pais> findAll() {
        return paisRepository.findAll();
    }

    public Pais findOrSaveByName(PaisDto dto){
        return paisRepository.findByName(dto.nmPais()).orElse(save(dto));
    }

    public Pais save(PaisDto paisDto){
        return paisRepository.save(paisMapper.toEntity(paisDto));
    }

    public Pais findById(Long id) {
        return paisRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pais não encontrado"));
    }

    public Pais update(Long id, PaisDto paisDto) {
        Pais pais = findById(id);
        paisMapper.updateEntityFromDto(pais, paisDto);
        return paisRepository.save(pais);
    }

    public boolean delete(Long id) {
        Pais pais = findById(id);
        paisRepository.delete(pais);
        return true;
    }
}
