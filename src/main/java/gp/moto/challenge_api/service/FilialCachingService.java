package gp.moto.challenge_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gp.moto.challenge_api.dto.filial.FilialDTO;
import gp.moto.challenge_api.dto.filial.FilialMapper;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Filial;
import gp.moto.challenge_api.repository.FilialRepository;

@Service
public class FilialCachingService {

    @Autowired
    private FilialRepository filialRepository;

    @Autowired
    private FilialMapper filialMapper;

    @Transactional
    public Filial criar(FilialDTO filialDTO){
        limparCache();
        return filialRepository.save(filialMapper.toFilial(filialDTO));
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "listarTodosFilial")
    public List<Filial> listarTodos(){
        return filialRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "buscarPorIdFilial", key = "#id")
    public Filial buscarPorId(Long id) throws ResourceNotFoundException {
        return filialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filial não encontrada"));
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "paginarFilial", key = "#page + '-' + #size")
    public Page<Filial> paginarFilial(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return filialRepository.findAll(pageable);
    }

    @Transactional
    public Filial alterar(Long id, FilialDTO filialDTO) {
        Filial filial = buscarPorId(id);
        filialMapper.updateEntityFromDto(filialDTO, filial);
        limparCache();
        return filialRepository.save(filial);
    }

    @Transactional
    public boolean deletar(Long id) throws ResourceNotFoundException {
        Filial filial = filialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filial não encontrada"));
        filialRepository.delete(filial);
        limparCache();
        return true;
    }

    @CacheEvict(value = {
        "listarTodosFilial", "buscarPorIdFilial", "paginarFilial"
    }, allEntries = true)
    public void limparCache() {
        System.out.println("Limpando cache...");
    }

}
