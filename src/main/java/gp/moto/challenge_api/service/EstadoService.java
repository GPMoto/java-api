package gp.moto.challenge_api.service;

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

    public List<Estado> findAll(){
        return estadoRepository.findAll();
    }

    public Estado findById(Long idEstado) {
        return estadoRepository.findById(idEstado).orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado"));
    }
}
