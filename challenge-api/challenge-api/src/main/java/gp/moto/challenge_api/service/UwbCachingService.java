package gp.moto.challenge_api.service;

import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.model.Uwb;
import gp.moto.challenge_api.repository.MotoRepository;
import gp.moto.challenge_api.repository.UwbRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UwbCachingService {

    @Autowired
    private UwbRepository uwbRepository;

    @Autowired
    private MotoRepository motoRepository;

    @CacheEvict()
    public Uwb desassociarUwbMoto (Long id) throws ResourceNotFoundException{
        Uwb uwb = uwbRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Uwb não encontrado"));
        uwb.setIdMoto(null);
        return uwbRepository.save(uwb);
    }

    @CacheEvict()
    public Uwb associarUwbMoto(Long idMoto, Long idUwb) throws ResourceNotFoundException {
        Uwb uwb = uwbRepository.findById(idUwb)
                .orElseThrow(() -> new ResourceNotFoundException("Uwb não encontrado"));
        Moto moto = motoRepository.findById(idMoto)
                .orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada"));

        uwb.setIdMoto(moto);
        return uwbRepository.save(uwb);
    }


}
