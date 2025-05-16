package gp.moto.challenge_api.service;

import gp.moto.challenge_api.dto.endereco.estado.EstadoDto;
import gp.moto.challenge_api.dto.endereco.estado.EstadoMapper;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Estado;
import gp.moto.challenge_api.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoMapper estadoMapper;

    public List<Estado> findAll(){
        return estadoRepository.findAll();
    }

    public Estado findById(Long idEstado) {
        return estadoRepository.findById(idEstado).orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado"));
    }

    public Estado update(Long id, EstadoDto estadoDto) {
        Estado estado = findById(id);
        estadoMapper.updateEntityFromDto(estadoDto, estado);
        return estadoRepository.save(estado);
    }

    public Estado save(EstadoDto estadoDto) {
        return estadoRepository.save(estadoMapper.toEntity(estadoDto));
    }

    public boolean delete(Long id) {
        Estado estado = findById(id);
        estadoRepository.delete(estado);
        return true;
    }
}
