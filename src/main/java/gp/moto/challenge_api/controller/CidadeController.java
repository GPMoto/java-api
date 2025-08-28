package gp.moto.challenge_api.controller;

import gp.moto.challenge_api.dto.endereco.cidade.CidadeDto;
import gp.moto.challenge_api.model.Cidade;
import gp.moto.challenge_api.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cidade")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<Cidade>> findAll(){
        return ResponseEntity.ok(cidadeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> findBydId(@PathVariable Long id){
        return ResponseEntity.ok(cidadeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Cidade> save(@RequestBody CidadeDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cidade> update(@PathVariable Long id, @RequestBody CidadeDto dto){
        return ResponseEntity.ok(cidadeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        cidadeService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
