package gp.moto.challenge_api.service;

import gp.moto.challenge_api.dto.filial.FilialDTO;
import gp.moto.challenge_api.dto.filial.FilialMapper;
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

    @Autowired
    private FilialMapper filialMapper;

    @Transactional
    public Filial criar(FilialDTO filialDTO){
        return filialRepository.save(filialMapper.toFilial(filialDTO));
    }


    @Transactional
    public List<Filial> listarTodos(){
        return filialRepository.findAll();
    }

    @Transactional
    public Filial buscarPorId(Long id) throws ResourceNotFoundException {
        return filialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filial não encontrada"));
    }

    @Transactional
    public Filial alterar(Long id, FilialDTO filialDTO) {
        Filial filial = buscarPorId(id);
        filialMapper.updateEntityFromDto(filialDTO, filial);
        return filialRepository.save(filial);
    }

    @Transactional
    public boolean deletar(Long id) throws ResourceNotFoundException {
        Filial filial = filialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filial não encontrada"));
        filialRepository.delete(filial);
        return true;
    }

    @Transactional
    public Page<Filial> paginarFilial(PageRequest pageRequest){
        return filialRepository.findAll(pageRequest);
    }



}
