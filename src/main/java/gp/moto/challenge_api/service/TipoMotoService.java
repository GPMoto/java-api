package gp.moto.challenge_api.service;

import gp.moto.challenge_api.dto.tipoMoto.TipoMotoDto;
import gp.moto.challenge_api.dto.tipoMoto.TipoMotoMapper;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.TipoMoto;
import gp.moto.challenge_api.repository.TipoMotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoMotoService {

    @Autowired
    private TipoMotoRepository tipoMotoRepository;

    @Autowired
    private TipoMotoMapper tipoMotoMapper;

    public TipoMoto save(TipoMotoDto dto) {
        return tipoMotoRepository.save(tipoMotoMapper.toEntity(dto));
    }

    public List<TipoMoto> findAll() {
        return tipoMotoRepository.findAll();
    }

    public TipoMoto update(Long id, TipoMotoDto dto) {
        TipoMoto tipoMoto = findById(id);
        tipoMotoMapper.updateEntityFromDto(dto, tipoMoto);
        return tipoMotoRepository.save(tipoMoto);
    }

    public TipoMoto findById(Long id) throws ResourceNotFoundException {
        return tipoMotoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tipo moto não encontrado!"));
    }

    public boolean delete(Long id) {
        TipoMoto tipoMoto = findById(id);
        tipoMotoRepository.delete(tipoMoto);
        return true;
    }
}
