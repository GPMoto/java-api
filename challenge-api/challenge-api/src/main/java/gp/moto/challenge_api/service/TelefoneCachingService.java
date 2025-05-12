package gp.moto.challenge_api.service;


import gp.moto.challenge_api.dto.TelefoneDTO;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Telefone;
import gp.moto.challenge_api.repository.TelefoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelefoneCachingService {

    @Autowired
    public TelefoneRepository telefoneRepository;


    @Transactional
    public void criar(TelefoneDTO telefoneDTO){
        Telefone tel = new Telefone(telefoneDTO);
        telefoneRepository.save(tel);
    }


    @Transactional
    public List<TelefoneDTO> listarTodos(){
        List<Telefone> tel = telefoneRepository.findAll();
        return tel.stream().map(TelefoneDTO ::new).toList();
    }

    @Transactional
    public TelefoneDTO buscarPorId(Long id) throws ResourceNotFoundException {
        return new TelefoneDTO(telefoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Telefone não encontrada")));
    }

    @Transactional
    public TelefoneDTO alterar(TelefoneDTO telefoneDTO) throws ResourceNotFoundException {
        if(!telefoneRepository.existsById(telefoneDTO.id_telefone())){
            throw new ResourceNotFoundException("Telefone não encontrada");
        }

        Telefone tel = new Telefone(telefoneDTO);
        return new TelefoneDTO(telefoneRepository.save(tel));
    }

    @Transactional
    public void deletar(Long id) throws ResourceNotFoundException {
        Telefone tel = telefoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Telefone não encontrada"));
        telefoneRepository.delete(tel);
    }




}
