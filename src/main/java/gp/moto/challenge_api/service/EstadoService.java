package gp.moto.challenge_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gp.moto.challenge_api.dto.endereco.estado.EstadoDto;
import gp.moto.challenge_api.dto.endereco.estado.EstadoMapper;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Estado;
import gp.moto.challenge_api.repository.EstadoRepository;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoMapper estadoMapper;

    @Transactional(readOnly = true)
    @Cacheable(value = "findAllEstado")
    public List<Estado> findAll(){
        return estadoRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findByIdEstado", key = "#idEstado")
    public Estado findById(Long idEstado) {
        return estadoRepository.findById(idEstado).orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado"));
    }

    @Transactional
    public Estado update(Long id, EstadoDto estadoDto) {
        Estado estado = findById(id);
        estadoMapper.updateEntityFromDto(estadoDto, estado);
        limparCache();
        return estadoRepository.save(estado);
    }

    @Transactional
    public Estado save(EstadoDto estadoDto) {
        limparCache();
        return estadoRepository.save(estadoMapper.toEntity(estadoDto));
    }

    @Transactional
    public boolean delete(Long id) {
        Estado estado = findById(id);
        estadoRepository.delete(estado);
        limparCache();
        return true;
    }

    @CacheEvict(value = {
            "findAllEstado", "findByIdEstado"
    }, allEntries = true)
    public void limparCache() {
        System.out.println("Limpando cache...");
    }

}
