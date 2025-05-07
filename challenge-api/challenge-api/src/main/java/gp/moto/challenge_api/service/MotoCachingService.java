package gp.moto.challenge_api.service;

import gp.moto.challenge_api.dto.MotoDTO;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.repository.MotoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoCachingService {

    @Autowired
    private MotoRepository motoRepository;


    @Transactional
    public void criar(MotoDTO motoDTO){
        Moto moto = new Moto(motoDTO);
        motoRepository.save(moto);
    }


    @Transactional
    public List<MotoDTO> listarTodos(){
        List<Moto> motos = motoRepository.findAll();
        return motos.stream().map(MotoDTO ::new).toList();
    }

    @Transactional
    public MotoDTO buscarPorId(Long id) throws ResourceNotFoundException {
        return new MotoDTO(motoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada")));
    }

    @Transactional
    public MotoDTO alterar(MotoDTO motoDTO) throws ResourceNotFoundException {
        if(!motoRepository.existsById(motoDTO.idMoto())){
            throw new ResourceNotFoundException("Moto não encontrada");
        }

        Moto moto = new Moto(motoDTO);
        return new MotoDTO(motoRepository.save(moto));
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



}
