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

import gp.moto.challenge_api.dto.moto.MotoDTO;
import gp.moto.challenge_api.dto.moto.MotoMapper;
import gp.moto.challenge_api.dto.moto.MotoProjection;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.repository.MotoRepository;

@Service
public class MotoCachingService {

    @Autowired
    private MotoRepository motoRepository;

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
    @Cacheable(value = "buscarPodIdMoto", key = "#id")
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

    @CacheEvict(value = {
            "listarTodosMotos",  "buscarPorIdProjectionMoto", "buscarPorIdMoto", "paginarMoto", "listarTodosPaginadasMoto"
    }, allEntries = true)
    public void limparCache(){
        System.out.println("Limpando cache...");
    }

}
