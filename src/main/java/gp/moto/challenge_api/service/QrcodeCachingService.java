package gp.moto.challenge_api.service;

import java.util.List;

import gp.moto.challenge_api.dto.qrcode.QrcodeDTO;
import gp.moto.challenge_api.dto.qrcode.QrcodeProjection;
import gp.moto.challenge_api.model.Qrcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gp.moto.challenge_api.dto.qrcode.QrcodeMapper;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Filial;
import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.repository.FilialRepository;
import gp.moto.challenge_api.repository.MotoRepository;
import gp.moto.challenge_api.repository.QrcodeRepository;


@Service
public class QrcodeCachingService {

    @Autowired
    private QrcodeRepository qrcodeRepository;

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private FilialRepository filialRepository;

    @Autowired
    private QrcodeMapper uwbMapper;

    @Transactional
    public Qrcode save(QrcodeDTO dto) {
        Qrcode qrcode = new Qrcode();
        qrcode.setVlQrcode(dto.vlQrcode());
        
        if (dto.idMoto() != null && dto.idMoto() > 0) {
            Moto moto = motoRepository.findById(dto.idMoto())
                    .orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada"));
            qrcode.setIdMoto(moto); 
        }
    
        Filial filial = filialRepository.findById(dto.idFilial())
                .orElseThrow(() -> new ResourceNotFoundException("Filial não encontrada"));
        qrcode.setFilial(filial);
        
    
        return qrcodeRepository.save(qrcode);
    }

    @Transactional
    public boolean delete(Long idUwb) throws ResourceNotFoundException {
        Qrcode qrcode = findById(idUwb);
        qrcodeRepository.delete(qrcode);
        limparCache();
        return true;
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findAllUwb")
    public List<QrcodeProjection> findAll() {
        return qrcodeRepository.findAll()
                .stream().map(uwb -> uwbMapper.toProjection(uwb))
                .toList();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findUwbById", key = "#id_uwb")
    public Qrcode findById(Long id_uwb) throws ResourceNotFoundException {
        return qrcodeRepository.findById(id_uwb)
                .orElseThrow(() -> new ResourceNotFoundException("Uwb não encontrado"));
    }


    @Cacheable(value = "paginarUwb", key = "#pageRequest")
    @Transactional(readOnly = true)
    public Page<Qrcode> paginarUwb(PageRequest pageRequest) {
        return qrcodeRepository.findAll(pageRequest);
    }


    @Transactional(readOnly = true)
    public Page<QrcodeProjection> findAllByFilialPage(Integer size, Integer page, Long idFilial) {
        Pageable pageable = PageRequest.of(page, size);
        return qrcodeRepository.findAllByFilialPage(pageable, idFilial)
                .map(uwb -> uwbMapper.toProjection(uwb));
    }

    @Transactional()
    public Qrcode updateMotoWithUwb(Long idUwb, Long idMoto, Long idFilial) {
        Qrcode qrcode = findById(idUwb);
        uwbMapper.updateEntityFromDto(new QrcodeDTO(qrcode.getVlQrcode(), idMoto, idFilial), qrcode);
        limparCache();
        return qrcodeRepository.save(qrcode);
    }

    @Transactional
    public Qrcode removeMotoWithUwb(Long idUwb) {
        Qrcode qrcode = findById(idUwb);
        uwbMapper.updateEntityFromDto(new QrcodeDTO(qrcode.getVlQrcode(), 0L, 0L), qrcode);
        limparCache();
        return qrcodeRepository.save(qrcode);
    }


    public Qrcode update(Long idUwb, QrcodeDTO qrcodeDto) {
        Qrcode qrcode = findById(idUwb);
        qrcode.setVlQrcode(qrcodeDto.vlQrcode());
        
        
        if (qrcodeDto.idMoto() != null && qrcodeDto.idMoto() > 0) {
            Moto moto = motoRepository.findById(qrcodeDto.idMoto())
                    .orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada com ID: " + qrcodeDto.idMoto()));
            qrcode.setIdMoto(moto);
        } else {
            qrcode.setIdMoto(null);
        }
        limparCache();
        return qrcodeRepository.save(qrcode);
    }

    @CacheEvict(value = {
        "findUwbById", "findAllUwb", "paginarUwb"
    }, allEntries = true)
    public void limparCache() {
        System.out.println("Removendo arquivos de cache!");
    }


}
