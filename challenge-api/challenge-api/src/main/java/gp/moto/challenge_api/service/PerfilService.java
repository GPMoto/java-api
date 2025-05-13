package gp.moto.challenge_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gp.moto.challenge_api.dto.perfil.PerfilDto;
import gp.moto.challenge_api.dto.perfil.PerfilMapper;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Perfil;
import gp.moto.challenge_api.repository.PerfilRepository;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PerfilMapper perfilMapper;

    public List<Perfil> findAll() {
        return perfilRepository.findAll();
    }

    public Perfil findById(Long id) {
        return perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil não encontrado com o ID: " + id));
    }

    public Perfil save(PerfilDto dto) {
        return perfilRepository.save(perfilMapper.toEntity(dto));
    }

    public Perfil update(Long id, PerfilDto dto) {
        Perfil perfil = findById(id);
        perfilMapper.updateEntityFromDto(dto, perfil);
        return perfilRepository.save(perfil);
    }

    public void delete(Long id) {
        Perfil perfil = findById(id);
        perfilRepository.delete(perfil);
    }
}
