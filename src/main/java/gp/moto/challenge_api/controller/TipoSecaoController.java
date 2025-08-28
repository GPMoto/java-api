package gp.moto.challenge_api.controller;

import gp.moto.challenge_api.dto.tipoSecao.TipoSecaoDto;
import gp.moto.challenge_api.model.TipoSecao;
import gp.moto.challenge_api.service.TipoSecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tipo-secao")
public class TipoSecaoController {

    @Autowired
    private TipoSecaoService tipoSecaoService;


    @GetMapping
    public ResponseEntity<List<TipoSecao>> findAll(){
        return ResponseEntity.ok(tipoSecaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoSecao> findById(@PathVariable Long id){
        return ResponseEntity.ok(tipoSecaoService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<TipoSecao> save(@RequestBody TipoSecaoDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoSecaoService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoSecao> update(@PathVariable Long id, @RequestBody TipoSecaoDto dto){
        return ResponseEntity.ok(tipoSecaoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        tipoSecaoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
