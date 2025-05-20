package gp.moto.challenge_api.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gp.moto.challenge_api.dto.telefone.TelefoneDTO;
import gp.moto.challenge_api.dto.telefone.TelefoneMapper;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Telefone;
import gp.moto.challenge_api.repository.TelefoneRepository;

@Service
public class TelefoneCachingService {

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private TelefoneMapper telefoneMapper;


    @Transactional
    public Telefone criar(TelefoneDTO telefoneDTO){
        limparCache();
        return telefoneRepository.save(telefoneMapper.toEntity(telefoneDTO));
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "listarTodosTelefone")
    public List<Telefone> listarTodos(){
        return telefoneRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "buscarPorIdTelefone", key = "#id")
    public Telefone buscarPorId(Long id) throws ResourceNotFoundException {
        return telefoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Telefone não encontrado!"));
    }

    @Transactional
    public Telefone alterar(Long id, TelefoneDTO telefoneDTO) {
       Telefone telefone = buscarPorId(id);
       telefoneMapper.updateTelefoneFromDto(telefoneDTO, telefone);
       limparCache();
       return telefoneRepository.save(telefone);
    }

    @Transactional
    public boolean deletar(Long id) throws ResourceNotFoundException {
        Telefone tel = telefoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Telefone não encontrada"));
        telefoneRepository.delete(tel);
        limparCache();
        return true;
    }

    @CacheEvict(value = {
        "listarTodosTelefone", "buscarPorIdTelefone"
    }, allEntries = true)
    public void limparCache(){
        System.out.println("Limpando cache...");
    }


}
