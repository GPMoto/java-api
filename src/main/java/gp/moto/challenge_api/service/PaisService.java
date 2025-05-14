package gp.moto.challenge_api.service;

import gp.moto.challenge_api.dto.endereco.pais.PaisDto;
import gp.moto.challenge_api.dto.endereco.pais.PaisMapper;
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

    private PaisRepository paisRepository;
    private PaisMapper paisMapper;
    public List<Pais> findAll() {
        return paisRepository.findAll();
    }

    public Pais findOrSaveByName(String nomePais){
        return paisRepository.findByName(nomePais).orElse(save(nomePais));
    }

    public Pais save(String nomePais){
        return paisRepository.save(paisMapper.toEntity(new PaisDto(nomePais)));
    }

}
