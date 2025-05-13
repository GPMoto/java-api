package gp.moto.challenge_api.service;

import gp.moto.challenge_api.dto.tipoSecao.TipoSecaoDto;
import gp.moto.challenge_api.dto.tipoSecao.TipoSecaoMapper;
import gp.moto.challenge_api.model.TipoSecao;
import gp.moto.challenge_api.repository.TipoSecaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoSecaoService {

    @Autowired
    private TipoSecaoRepository tipoSecaoRepository;

    @Autowired
    private TipoSecaoMapper tipoSecaoMapper;

    public List<TipoSecao> findAll() {
        return tipoSecaoRepository.findAll();
    }

    public TipoSecao findById(Long id) {
        Optional<TipoSecao> tipoSecao = tipoSecaoRepository.findById(id);
        return tipoSecao.orElseThrow(() -> new RuntimeException("TipoSecao não encontrado com o ID: " + id));
    }

    public TipoSecao save(TipoSecaoDto dto) {
        return tipoSecaoRepository.save(tipoSecaoMapper.toEntity(dto));
    }

    public TipoSecao update(Long id, TipoSecaoDto dto) {
        TipoSecao tipoSecao = findById(id);
        tipoSecaoMapper.updateEntityFromDto(dto, tipoSecao);
        return tipoSecaoRepository.save(tipoSecao);
    }

    public void delete(Long id) {
        TipoSecao tipoSecao = findById(id);
        tipoSecaoRepository.delete(tipoSecao);
    }
}
