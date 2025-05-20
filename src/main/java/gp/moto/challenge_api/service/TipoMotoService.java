package gp.moto.challenge_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gp.moto.challenge_api.dto.tipoMoto.TipoMotoDto;
import gp.moto.challenge_api.dto.tipoMoto.TipoMotoMapper;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.TipoMoto;
import gp.moto.challenge_api.repository.TipoMotoRepository;

@Service
public class TipoMotoService {

    @Autowired
    private TipoMotoRepository tipoMotoRepository;

    @Autowired
    private TipoMotoMapper tipoMotoMapper;

    @Transactional
    public TipoMoto save(TipoMotoDto dto) {
        limparCache();
        return tipoMotoRepository.save(tipoMotoMapper.toEntity(dto));
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findAllTipoMoto")
    public List<TipoMoto> findAll() {
        return tipoMotoRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findByIdTipoMoto", key = "#id")
    public TipoMoto findById(Long id) throws ResourceNotFoundException {
        return tipoMotoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tipo moto não encontrado!"));
    }

    @Transactional
    public TipoMoto update(Long id, TipoMotoDto dto) {
        TipoMoto tipoMoto = findById(id);
        tipoMotoMapper.updateEntityFromDto(dto, tipoMoto);
        limparCache();
        return tipoMotoRepository.save(tipoMoto);
    }

    @Transactional
    public boolean delete(Long id) {
        TipoMoto tipoMoto = findById(id);
        tipoMotoRepository.delete(tipoMoto);
        limparCache();
        return true;
    }

    @Transactional
    @CacheEvict(value = {
        "findAllTipoMoto", "findByIdTipoMoto"
    }, allEntries = true)
    public void limparCache() {
        System.out.println("Limpando o cache...");
    }
}
