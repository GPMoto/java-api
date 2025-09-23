package gp.moto.challenge_api.controller;

import gp.moto.challenge_api.dto.tipoMoto.TipoMotoDto;
import gp.moto.challenge_api.dto.tipoMoto.TipoMotoMapper;
import gp.moto.challenge_api.model.TipoMoto;
import gp.moto.challenge_api.service.TipoMotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tipo-moto")
public class TipoMotoController {

    @Autowired
    private TipoMotoService tipoMotoService;


    @PostMapping
    public ResponseEntity<TipoMoto> save(TipoMotoDto tipoMotoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoMotoService.save(tipoMotoDto));
    }

    @GetMapping
    public ResponseEntity<List<TipoMoto>> findAll(){
        return ResponseEntity.ok(tipoMotoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoMoto> findById(@PathVariable Long id){
        return ResponseEntity.ok(tipoMotoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoMoto> update(@PathVariable Long id, @RequestBody TipoMotoDto dto){
        return ResponseEntity.ok(tipoMotoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        tipoMotoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
