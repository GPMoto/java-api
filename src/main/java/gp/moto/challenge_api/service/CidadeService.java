package gp.moto.challenge_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gp.moto.challenge_api.dto.endereco.cidade.CidadeDto;
import gp.moto.challenge_api.dto.endereco.cidade.CidadeMapper;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Cidade;
import gp.moto.challenge_api.repository.CidadeRepository;

@Service
public class CidadeService {

    @Autowired
    private  CidadeRepository cidadeRepository;

    @Autowired
    private CidadeMapper cidadeMapper;

    @Transactional(readOnly = true)
    @Cacheable(value = "findAllCidade")
    public List<Cidade> findAll() {
        return cidadeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findByIdCidade", key = "#id")
    public Cidade findById(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cidade nção foi encontrada!"));
    }

    @Transactional
    public Cidade save(CidadeDto dto) {
        limparCache();
        return cidadeRepository.save(cidadeMapper.toEntity(dto));
    }


    @Transactional
    public boolean delete(Long id) {
        Cidade cidade = findById(id);
        cidadeRepository.delete(cidade);
        limparCache();
        return true;
    }

    @Transactional
    public Cidade update(Long id, CidadeDto dto) {
        Cidade cidade = findById(id);
        cidadeMapper.updateEntityFromDto(dto, cidade);
        limparCache();
        return cidadeRepository.save(cidade);
    }

    @CacheEvict(value = {
            "findAllCidade", "findByIdCidade"
    }, allEntries = true)
    public void limparCache(){
        System.out.println("Limpando cache...");
    }
}
