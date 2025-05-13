package gp.moto.challenge_api.service;

import gp.moto.challenge_api.dto.moto.MotoDTO;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.dto.moto.MotoMapper;
import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MotoCachingService {

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private MotoMapper motoMapper;


    @Transactional
    public Moto criar(MotoDTO motoDTO){
        return motoRepository.save(motoMapper.toMoto(motoDTO));
    }


    @Transactional(readOnly = true)
    public List<Moto> listarTodos(){
        return motoRepository.findAll();
    }

    @Transactional
    public Moto buscarPorId(Long id) throws ResourceNotFoundException {
        return motoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada"));
    }

    @Transactional
    public Moto alterar(Long id, MotoDTO motoDTO) throws ResourceNotFoundException {
        Moto moto = buscarPorId(id);
        motoMapper.updateEntityFromDto(motoDTO, moto);
        return motoRepository.save(moto);
    }

    @Transactional
    public void deletar(Long id) throws ResourceNotFoundException {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("moto não encontrada"));
        motoRepository.delete(moto);
    }

    @Transactional
    public Page<Moto> paginarMoto(PageRequest pageRequest){
        return motoRepository.findAll(pageRequest);
    }


    @Transactional(readOnly = true)
    public Page<Moto> listarTodasPaginadas(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return motoRepository.findAll(pageable);
    }

}
