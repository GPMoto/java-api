package gp.moto.challenge_api.controller;

import gp.moto.challenge_api.dto.endereco.pais.PaisDto;
import gp.moto.challenge_api.model.Pais;
import gp.moto.challenge_api.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pais")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @GetMapping
    public ResponseEntity<List<Pais>> findAll(){
        return ResponseEntity.ok(paisService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pais> findById(@PathVariable Long id){
        return ResponseEntity.ok(paisService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Pais> save(@RequestBody PaisDto paisDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(paisService.save(paisDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pais> update(@PathVariable Long id, @RequestBody PaisDto paisDto){
        return ResponseEntity.ok(paisService.update(id, paisDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        paisService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
