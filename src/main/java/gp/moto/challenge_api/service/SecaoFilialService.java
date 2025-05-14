package gp.moto.challenge_api.service;

import gp.moto.challenge_api.dto.secaoFilial.SecaoFilialDto;
import gp.moto.challenge_api.dto.secaoFilial.SecaoFilialMapper;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.SecaoFilial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gp.moto.challenge_api.repository.SecaoFilialRepository;

import java.util.List;

@Service
public class SecaoFilialService {

    @Autowired
    private SecaoFilialRepository secaoFilialRepository;

    @Autowired
    private SecaoFilialMapper secaoFilialMapper;


    public SecaoFilial save(SecaoFilialDto dto) {
        return secaoFilialRepository.save(secaoFilialMapper.toEntity(dto));
    }

    public List<SecaoFilial> findAll() {
        return secaoFilialRepository.findAll();
    }


    public SecaoFilial findById(Long id) throws ResourceNotFoundException{
        return secaoFilialRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Seção de filial não encontrada!"));
    }

    public List<SecaoFilial> findAllByFilial(Long idFilial) {
        return secaoFilialRepository.findAllByFilial(idFilial);
    }

    public SecaoFilial update(Long id, SecaoFilialDto dto) {
        SecaoFilial secaoFilial = findById(id);
        secaoFilialMapper.updateEntityFromDto(dto, secaoFilial);
        return secaoFilialRepository.save(secaoFilial);
    }

    public boolean delete(Long id) {
        SecaoFilial secaoFilial = findById(id);
        secaoFilialRepository.delete(secaoFilial);
        return true;
    }
}
