package gp.moto.challenge_api.service;


import gp.moto.challenge_api.dto.telefone.TelefoneDTO;
import gp.moto.challenge_api.dto.telefone.TelefoneMapper;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Telefone;
import gp.moto.challenge_api.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TelefoneCachingService {

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private TelefoneMapper telefoneMapper;


    @Transactional
    public Telefone criar(TelefoneDTO telefoneDTO){
        return telefoneRepository.save(telefoneMapper.toEntity(telefoneDTO));
    }


    @Transactional()
    public List<Telefone> listarTodos(){
        return telefoneRepository.findAll();
    }

    @Transactional
    public Telefone buscarPorId(Long id) throws ResourceNotFoundException {
        return telefoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Telefone não encontrado!"));
    }

    @Transactional
    public Telefone alterar(Long id, TelefoneDTO telefoneDTO) {
       Telefone telefone = buscarPorId(id);
       telefoneMapper.updateTelefoneFromDto(telefoneDTO, telefone);
       return telefoneRepository.save(telefone);
    }

    @Transactional
    public boolean deletar(Long id) throws ResourceNotFoundException {
        Telefone tel = telefoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Telefone não encontrada"));
        telefoneRepository.delete(tel);
        return true;
    }




}
