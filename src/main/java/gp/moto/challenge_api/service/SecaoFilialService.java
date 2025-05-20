package gp.moto.challenge_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gp.moto.challenge_api.dto.secaoFilial.SecaoFilialDto;
import gp.moto.challenge_api.dto.secaoFilial.SecaoFilialMapper;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.SecaoFilial;
import gp.moto.challenge_api.repository.SecaoFilialRepository;

@Service
public class SecaoFilialService {

    @Autowired
    private SecaoFilialRepository secaoFilialRepository;

    @Autowired
    private SecaoFilialMapper secaoFilialMapper;

    @Transactional()
    public SecaoFilial save(SecaoFilialDto dto) {
        limparCache();
        return secaoFilialRepository.save(secaoFilialMapper.toEntity(dto));
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findAllSecaoFilial")
    public List<SecaoFilial> findAll() {
        return secaoFilialRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findByIdSecaoFilial", key = "#id")
    public SecaoFilial findById(Long id) throws ResourceNotFoundException{
        return secaoFilialRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Seção de filial não encontrada!"));
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findAllByFilialSecaoFilial", key = "#idFilial")
    public List<SecaoFilial> findAllByFilial(Long idFilial) {
        return secaoFilialRepository.findAllByFilial(idFilial);
    }

    @Transactional
    public SecaoFilial update(Long id, SecaoFilialDto dto) {
        SecaoFilial secaoFilial = findById(id);
        secaoFilialMapper.updateEntityFromDto(dto, secaoFilial);
        limparCache();
        return secaoFilialRepository.save(secaoFilial);
    }

    @Transactional
    public boolean delete(Long id) {
        SecaoFilial secaoFilial = findById(id);
        secaoFilialRepository.delete(secaoFilial);
        limparCache();
        return true;
    }

    @CacheEvict(value = {
            "findAllSecaoFilial", "findByIdSecaoFilial", "findAllByFilialSecaoFilial"
    }, allEntries = true)
    public void limparCache() {
        System.out.println("Limpando cache...");
    }
}
