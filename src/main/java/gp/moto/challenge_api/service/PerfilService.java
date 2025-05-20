package gp.moto.challenge_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    @Cacheable(value = "findAllPerfil")
    public List<Perfil> findAll() {
        return perfilRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findByIdPerfil", key = "#id")
    public Perfil findById(Long id) {
        return perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil não encontrado com o ID: " + id));
    }

    @Transactional
    public Perfil save(PerfilDto dto) {
        limparCache();
        return perfilRepository.save(perfilMapper.toEntity(dto));
    }

    @Transactional
    public Perfil update(Long id, PerfilDto dto) {
        Perfil perfil = findById(id);
        perfilMapper.updateEntityFromDto(dto, perfil);
        limparCache();
        return perfilRepository.save(perfil);
    }

    @Transactional
    public boolean delete(Long id) {
        Perfil perfil = findById(id);
        perfilRepository.delete(perfil);
        limparCache();
        return true;
    }

    @CacheEvict(value = {
            "findAllPerfil", "findByIdPerfil"
    }, allEntries = true)
    public void limparCache() {
        System.out.println("Limpando cache...");
    }
}
