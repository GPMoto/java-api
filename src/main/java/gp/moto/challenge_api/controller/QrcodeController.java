package gp.moto.challenge_api.controller;

import gp.moto.challenge_api.dto.qrcode.QrcodeDTO;
import gp.moto.challenge_api.dto.qrcode.QrcodeProjection;
import gp.moto.challenge_api.model.Qrcode;
import gp.moto.challenge_api.service.QrcodeCachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/qrcode")
public class QrcodeController {

    @Autowired
    private QrcodeCachingService qrcodeCachingService;


    @GetMapping
    public ResponseEntity<List<QrcodeProjection>> findAll(){
        return ResponseEntity.ok(qrcodeCachingService.findAll());
    }

    @GetMapping("/page/filial/{idFilial}")
    public ResponseEntity<Page<QrcodeProjection>> findAllByFilialPage(@PathVariable Long idFilial, @RequestParam(value = "quantidade", defaultValue = "10") Integer size, @RequestParam(value = "pagina", defaultValue = "0") Integer page){
        return ResponseEntity.ok(qrcodeCachingService.findAllByFilialPage(size, page, idFilial));
    }

    @GetMapping("/{idQrcode}")
    public ResponseEntity<Qrcode> findById(@PathVariable Long idQrcode){
        return ResponseEntity.ok(qrcodeCachingService.findById(idQrcode));
    }

    @PostMapping
    public ResponseEntity<Qrcode> save(@RequestBody QrcodeDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(qrcodeCachingService.save(dto));
    }

//    @PatchMapping("/{idUwb}/mudar-moto/")
//    public ResponseEntity<Uwb> updateMotoWithUwb(@PathVariable Long idUwb, @RequestParam Long idMoto){
//        return ResponseEntity.ok(uwbCachingService.updateMotoWithUwb(idUwb, idMoto));
//    }

    @PutMapping("/{idQrcode}")
    public ResponseEntity<Qrcode> update(@PathVariable Long idQrcode, @RequestBody QrcodeDTO dto){
        return ResponseEntity.ok(qrcodeCachingService.update(idQrcode, dto));
    }

//    @PatchMapping("{idUwb}/remover-moto")
//    public ResponseEntity<Uwb> removeMotoWithUwb(@PathVariable Long idUwb){
//        return ResponseEntity.ok(uwbCachingService.removeMotoWithUwb(idUwb));
//    }
}
