package gp.moto.challenge_api.service;

import gp.moto.challenge_api.dto.ContatoDTO;
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

    @Transactional
    public void criar(ContatoDTO contatoDTO, Long idTelefone){
        Telefone telefone = telefoneRepository.findById(idTelefone)
                .orElseThrow(() -> new RuntimeException("Telefone não encontrado"));


        Contato contato = new Contato(contatoDTO);
        contatoRepository.save(contato);


    }


    @Transactional(readOnly = true)
    public List<ContatoDTO> listarTodos(){
        List<Contato> contatos = contatoRepository.findAll();
        return contatos.stream().map(ContatoDTO::new).toList();
    }


    @Transactional(readOnly = true)
    public ContatoDTO buscarPorId(Long id) throws ResourceNotFoundException {
        return new ContatoDTO(contatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado")));
    }


    @Transactional
    public ContatoDTO alterar(ContatoDTO contatoDTO) throws ResourceNotFoundException {
        if(!contatoRepository.existsById(contatoDTO.idContato())){
            throw new ResourceNotFoundException("Contato não encontrado");
        }

        Contato contato = new Contato(contatoDTO);
        return new ContatoDTO(contatoRepository.save(contato));
    }

    @Transactional
    public void deletar(Long id) throws ResourceNotFoundException {
        Contato contato = contatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado"));
        contatoRepository.delete(contato);
    }

    @Transactional(readOnly = true)
    public Page<Contato> paginarContato(PageRequest pageRequest){
        return contatoRepository.findAll(pageRequest);
    }

}
