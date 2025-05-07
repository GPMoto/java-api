package gp.moto.challenge_api.service;

import gp.moto.challenge_api.dto.FilialDTO;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Filial;
import gp.moto.challenge_api.repository.FilialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilialCachingService {

    @Autowired
    private FilialRepository filialRepository;


    @Transactional
    public void criar(FilialDTO filialDTO){
        Filial filial = new Filial(filialDTO);
        filialRepository.save(filial);
    }


    @Transactional
    public List<FilialDTO> listarTodos(){
        List<Filial> filial = filialRepository.findAll();
        return filial.stream().map(FilialDTO ::new).toList();
    }

    @Transactional
    public FilialDTO buscarPorId(Long id) throws ResourceNotFoundException {
        return new FilialDTO(filialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filial não encontrada")));
    }

    @Transactional
    public FilialDTO alterar(FilialDTO filialDTO) throws ResourceNotFoundException {
        if(!filialRepository.existsById(filialDTO.idFilial())){
            throw new ResourceNotFoundException("Filial não encontrada");
        }

        Filial filial = new Filial(filialDTO);
        return new FilialDTO(filialRepository.save(filial));
    }

    @Transactional
    public void deletar(Long id) throws ResourceNotFoundException {
        Filial filial = filialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filial não encontrada"));
        filialRepository.delete(filial);
    }

    @Transactional
    public Page<Filial> paginarFilial(PageRequest pageRequest){
        return filialRepository.findAll(pageRequest);
    }



}
