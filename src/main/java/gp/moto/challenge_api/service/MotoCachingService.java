package gp.moto.challenge_api.service;

import java.util.List;
import java.util.Optional;

import gp.moto.challenge_api.model.Qrcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gp.moto.challenge_api.dto.moto.MotoDTO;
import gp.moto.challenge_api.dto.moto.MotoMapper;
import gp.moto.challenge_api.dto.moto.MotoProjection;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.repository.MotoRepository;
import gp.moto.challenge_api.repository.QrcodeRepository;

@Service
public class MotoCachingService {

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private QrcodeRepository qrcodeRepository;

    @Autowired
    private MotoMapper motoMapper;

    @Transactional
    public Moto criar(MotoDTO motoDTO) {
        limparCache();
        return motoRepository.save(motoMapper.toMoto(motoDTO));
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "listarTodosMotos")
    public List<MotoProjection> listarTodos() {
        return motoRepository.findAll()
                .stream()
                .map(moto -> motoMapper.toProjection(moto))
                .toList();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "buscarPorIdProjectionMoto", key = "#id")
    public MotoProjection buscarPorIdProjection(Long id) throws ResourceNotFoundException {
        return motoMapper.toProjection(motoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada")));
    }


    @Transactional(readOnly = true)
    @Cacheable(value = "buscarPorIdMoto", key = "#id")
    public Moto buscarPorId(Long id) throws ResourceNotFoundException {
        return motoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada"));
    }

    @Transactional
    public Moto alterar(Long id, MotoDTO motoDTO) throws ResourceNotFoundException {
        Moto moto = buscarPorId(id);
        motoMapper.updateEntityFromDto(motoDTO, moto);
        limparCache();
        return motoRepository.save(moto);
    }

    @Transactional
    public boolean deletar(Long id) throws ResourceNotFoundException {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("moto não encontrada"));

        Optional<Qrcode> uwb = qrcodeRepository.findByIdMoto(moto);
        if (uwb.isPresent()) {
            uwb.get().setIdMoto(null);
            qrcodeRepository.save(uwb.get());
        }
        motoRepository.delete(moto);
        limparCache();
        return true;
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "paginarMoto", key = "#page + '-' + #size")
    public Page<Moto> paginarMoto(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return motoRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "listarTodosPaginadasMoto", key = "#idFilial + '-' + #page + '-' + #size")
    public Page<MotoProjection> listarTodasPaginadasFilial(Long idFilial, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Moto> motos = motoRepository.findAllByFilial(pageable, idFilial);
        return motoMapper.toProjection(motos);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "listarTodosPaginadasMotoFull", key = "#idFilial + '-' + #search + '-' + #page + '-' + #size")
    public Page<Moto> listarTodasPaginadasFilialFull(Long idFilial, String search, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        
        if (search != null && !search.trim().isEmpty()) {
            return motoRepository.findAllByFilialWithSearch(pageable, idFilial, search.trim());
        }
        
        return motoRepository.findAllByFilial(pageable, idFilial);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "listarTodasPaginadasMotoSecaoFilial", key = "#idFilial + '-' + #page + '-' + #size")
    public Page<Moto> listarTodasPaginadasSecaoFilial(Long idSecaoFilial, String search, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        if (search != null && !search.trim().isEmpty()) {
            return motoRepository.findByIdSecaoFilialWithSearch(pageable, idSecaoFilial, search.trim());
        }

        return motoRepository.findByIdSecaoFilial(pageable, idSecaoFilial);
    }

    @CacheEvict(value = {
        "listarTodosMotos",
        "buscarPorIdProjectionMoto",
        "buscarPorIdMoto",
        "paginarMoto",
        "listarTodosPaginadasMoto",
        "listarTodosPaginadasMotoFull",
        "listarTodasPaginadasMotoSecaoFilial"
    }, allEntries = true)
    public void limparCache() {
        System.out.println("Limpando cache...");
    }

}
