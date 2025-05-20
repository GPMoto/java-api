package gp.moto.challenge_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gp.moto.challenge_api.dto.endereco.pais.PaisDto;
import gp.moto.challenge_api.dto.endereco.pais.PaisMapper;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Pais;
import gp.moto.challenge_api.repository.PaisRepository;

@Service
public class PaisService {

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private  PaisMapper paisMapper;

    @Transactional(readOnly = true)
    @Cacheable(value = "findAllPais")
    public List<Pais> findAll() {
        return paisRepository.findAll();
    }


    @Transactional()
    public Pais save(PaisDto paisDto){
        limparCache();
        return paisRepository.save(paisMapper.toEntity(paisDto));
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findByIdPais", key = "#id")
    public Pais findById(Long id) {
        return paisRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pais não encontrado"));
    }

    @Transactional
    public Pais update(Long id, PaisDto paisDto) {
        Pais pais = findById(id);
        paisMapper.updateEntityFromDto(pais, paisDto);
        limparCache();
        return paisRepository.save(pais);
    }

    @Transactional
    public boolean delete(Long id) {
        Pais pais = findById(id);
        paisRepository.delete(pais);
        limparCache();
        return true;
    }

    @CacheEvict(value = {
            "findAllPais"
    }, allEntries = true)
    public void limparCache(){
        System.out.println("Limpando cache...");
    }
}
