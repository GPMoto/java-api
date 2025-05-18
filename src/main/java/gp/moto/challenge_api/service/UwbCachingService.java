package gp.moto.challenge_api.service;

import gp.moto.challenge_api.dto.uwb.UwbDTO;
import gp.moto.challenge_api.dto.uwb.UwbMapper;
import gp.moto.challenge_api.dto.uwb.UwbProjection;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.model.Uwb;
import gp.moto.challenge_api.repository.MotoRepository;
import gp.moto.challenge_api.repository.UwbRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class UwbCachingService {

    @Autowired
    private UwbRepository uwbRepository;

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private UwbMapper uwbMapper;

    @Transactional
    public Uwb save(UwbDTO dto) {
        limparCache();
        return uwbRepository.save(uwbMapper.toEntity(dto));
    }

    @Transactional
    public boolean delete(Long idUwb) throws ResourceNotFoundException {
        Uwb uwb = findById(idUwb);
        uwbRepository.delete(uwb);
        limparCache();
        return true;
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findAllUwb")
    public List<UwbProjection> findAll() {
        return uwbRepository.findAll()
                .stream().map(uwb -> uwbMapper.toProjection(uwb))
                .toList();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findUwbById", key = "#id_uwb")
    public Uwb findById(Long id_uwb) throws ResourceNotFoundException {
        return uwbRepository.findById(id_uwb)
                .orElseThrow(() -> new ResourceNotFoundException("Uwb não encontrado"));
    }


    @Cacheable(value = "paginarUwb", key = "#pageRequest")
    @Transactional(readOnly = true)
    public Page<Uwb> paginarUwb(PageRequest pageRequest) {
        return uwbRepository.findAll(pageRequest);
    }


    @Transactional(readOnly = true)
    public Page<UwbProjection> findAllByFilialPage(Integer size, Integer page, Long idFilial) {
        Pageable pageable = PageRequest.of(size, page);
        return uwbRepository.findAllByFilialPage(pageable, idFilial)
                .map(uwb -> uwbMapper.toProjection(uwb));
    }

    @Transactional()
    public Uwb updateMotoWithUwb(Long idUwb, Long idMoto) {
        Uwb uwb = findById(idUwb);
        uwbMapper.updateEntityFromDto(new UwbDTO(uwb.getVlUwb(), idMoto), uwb);
        limparCache();
        return uwbRepository.save(uwb);
    }

    @Transactional
    public Uwb removeMotoWithUwb(Long idUwb) {
        Uwb uwb = findById(idUwb);
        uwbMapper.updateEntityFromDto(new UwbDTO(uwb.getVlUwb(), 0L), uwb);
        limparCache();
        return uwbRepository.save(uwb);
    }

    @CacheEvict(value = {"findUwbById", "findAllUwb"}, allEntries = true)
    public void limparCache() {
        System.out.println("Removendo arquivos de cache!");
    }

    public Uwb update(Long idUwb, UwbDTO uwbDto) {
        Uwb uwb = findById(idUwb);
        uwbMapper.updateEntityFromDto(uwbDto, uwb);
        limparCache();
        return uwbRepository.save(uwb);
    }
}
