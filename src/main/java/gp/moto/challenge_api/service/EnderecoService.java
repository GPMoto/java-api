package gp.moto.challenge_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gp.moto.challenge_api.dto.endereco.EnderecoDto;
import gp.moto.challenge_api.dto.endereco.EnderecoMapper;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Endereco;
import gp.moto.challenge_api.repository.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Autowired
    private PaisService paisService;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private EstadoService estadoService;

    @Transactional(readOnly = true)
    @Cacheable(value = "findAllEndereco")
    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findByIdEndereco", key = "#id")
    public Endereco findById(Long id) {
        return enderecoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
    }

    @Transactional
    public Endereco save(EnderecoDto dto) {
        Endereco endereco = enderecoMapper.toEntity(dto);
        limparCache();
        return enderecoRepository.save(endereco);
    }

    @Transactional
    public Endereco update(Long id, EnderecoDto dto) {
        Endereco endereco = findById(id);
        enderecoMapper.updateEntityFromDto(dto, endereco);
        limparCache();
        return enderecoRepository.save(endereco);
    }

    @Transactional
    public boolean delete(Long id) {
        Endereco endereco = findById(id);
        enderecoRepository.delete(endereco);
        limparCache();
        return true;
    }

    @CacheEvict(value = {
            "findAllEndereco", "findByIdEndereco"
    }, allEntries = true)
    public void limparCache() {
        System.out.println("Limpando cache");
    }
}
