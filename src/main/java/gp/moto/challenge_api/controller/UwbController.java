package gp.moto.challenge_api.controller;

import gp.moto.challenge_api.dto.uwb.UwbDTO;
import gp.moto.challenge_api.dto.uwb.UwbProjection;
import gp.moto.challenge_api.model.Uwb;
import gp.moto.challenge_api.service.UwbCachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uwb")
public class UwbController {

    @Autowired
    private UwbCachingService uwbCachingService;


    @GetMapping
    public ResponseEntity<List<UwbProjection>> findAll(){
        return ResponseEntity.ok(uwbCachingService.findAll());
    }

    @GetMapping("/page/filial/{idFilial}")
    public ResponseEntity<Page<UwbProjection>> findAllByFilialPage(@PathVariable Long idFilial, @RequestParam(value = "quantidade", defaultValue = "10") Integer size, @RequestParam(value = "pagina", defaultValue = "0") Integer page){
        return ResponseEntity.ok(uwbCachingService.findAllByFilialPage(size, page, idFilial));
    }

    @GetMapping("/{idUwb}")
    public ResponseEntity<Uwb> findById(@PathVariable Long idUwb){
        return ResponseEntity.ok(uwbCachingService.findById(idUwb));
    }

    @PostMapping
    public ResponseEntity<Uwb> save(@RequestBody UwbDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(uwbCachingService.save(dto));
    }

//    @PatchMapping("/{idUwb}/mudar-moto/")
//    public ResponseEntity<Uwb> updateMotoWithUwb(@PathVariable Long idUwb, @RequestParam Long idMoto){
//        return ResponseEntity.ok(uwbCachingService.updateMotoWithUwb(idUwb, idMoto));
//    }

    @PutMapping("/{idUwb}")
    public ResponseEntity<Uwb> update(@PathVariable Long idUwb, @RequestBody UwbDTO dto){
        return ResponseEntity.ok(uwbCachingService.update(idUwb, dto));
    }

//    @PatchMapping("{idUwb}/remover-moto")
//    public ResponseEntity<Uwb> removeMotoWithUwb(@PathVariable Long idUwb){
//        return ResponseEntity.ok(uwbCachingService.removeMotoWithUwb(idUwb));
//    }
}
