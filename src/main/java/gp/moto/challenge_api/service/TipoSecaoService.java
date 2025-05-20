package gp.moto.challenge_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gp.moto.challenge_api.dto.tipoSecao.TipoSecaoDto;
import gp.moto.challenge_api.dto.tipoSecao.TipoSecaoMapper;
import gp.moto.challenge_api.model.TipoSecao;
import gp.moto.challenge_api.repository.TipoSecaoRepository;

@Service
public class TipoSecaoService {

    @Autowired
    private TipoSecaoRepository tipoSecaoRepository;

    @Autowired
    private TipoSecaoMapper tipoSecaoMapper;

    @Transactional(readOnly = true)
    @Cacheable(value = "findAllTipoSecao")
    public List<TipoSecao> findAll() {
        return tipoSecaoRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findByIdTipoSecao", key = "#id")
    public TipoSecao findById(Long id) {
        Optional<TipoSecao> tipoSecao = tipoSecaoRepository.findById(id);
        return tipoSecao.orElseThrow(() -> new RuntimeException("TipoSecao não encontrado com o ID: " + id));
    }

    @Transactional
    public TipoSecao save(TipoSecaoDto dto) {
        limparCache();
        return tipoSecaoRepository.save(tipoSecaoMapper.toEntity(dto));
    }

    @Transactional
    public TipoSecao update(Long id, TipoSecaoDto dto) {
        TipoSecao tipoSecao = findById(id);
        tipoSecaoMapper.updateEntityFromDto(dto, tipoSecao);
        limparCache();
        return tipoSecaoRepository.save(tipoSecao);
    }

    @Transactional
    public void delete(Long id) {
        TipoSecao tipoSecao = findById(id);
        tipoSecaoRepository.delete(tipoSecao);
        limparCache();
    }

    @CacheEvict(value = {
        "findAllTipoSecao", "findByIdTipoSecao"
    }, allEntries = true)
    public void limparCache(){
        System.out.println("Limpando cache...");
    }
}
