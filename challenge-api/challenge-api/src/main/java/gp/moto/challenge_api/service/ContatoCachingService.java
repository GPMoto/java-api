package gp.moto.challenge_api.service;

import gp.moto.challenge_api.dto.contato.ContatoDTO;
import gp.moto.challenge_api.dto.contato.ContatoMapper;
import gp.moto.challenge_api.dto.contato.ContatoProjection;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Contato;
import gp.moto.challenge_api.model.Telefone;
import gp.moto.challenge_api.repository.ContatoRepository;
import gp.moto.challenge_api.repository.TelefoneRepository;
import org.springframework.data.domain.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//Tirei os construtores de conversão de contato e do dto já que vamos manter toda esse lógica aqui
//usa o ModelMapper para fazer essa lógica de conversão


@Service
public class ContatoCachingService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private ContatoMapper contatoMapper;

    @Transactional
    public Contato criar(ContatoDTO contatoDTO) throws ResourceNotFoundException{
        Telefone telefone = telefoneRepository.findById(contatoDTO.idTelefone())
                .orElseThrow(() -> new ResourceNotFoundException("Telefone não encontrado"));
        return contatoRepository.save(contatoMapper.toEntity(contatoDTO));
    }


    @Transactional(readOnly = true)
    public List<Contato> listarTodos() {
        return contatoRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Contato buscarPorId(Long id) throws ResourceNotFoundException {
        return contatoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado!"));
    }


    @Transactional
    public Contato alterar(Long id, ContatoDTO contatoDTO) throws ResourceNotFoundException {
        Contato contato = contatoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado!"));
        contatoMapper.updateEntityFromDto(contatoDTO, contato);
        return contatoRepository.save(contato);
    }

    @Transactional
    public boolean deletar(Long id) throws ResourceNotFoundException {
        Contato contato = contatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado"));
        contatoRepository.delete(contato);
        return true;
    }

    @Transactional(readOnly = true)
    public Page<Contato> paginarContato(PageRequest pageRequest) {
        return contatoRepository.findAll(pageRequest);
    }

}
