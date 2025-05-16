package gp.moto.challenge_api.controller;

import gp.moto.challenge_api.dto.endereco.estado.EstadoDto;
import gp.moto.challenge_api.model.Estado;
import gp.moto.challenge_api.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estado")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public ResponseEntity<List<Estado>> findAll(){
        return ResponseEntity.ok(estadoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> findById(@PathVariable Long id){
        return ResponseEntity.ok(estadoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Estado> save(@RequestBody EstadoDto estadoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoService.save(estadoDto));
    }

    @PutMapping("/{id}")
    ResponseEntity<Estado> update(@PathVariable Long id, @RequestBody EstadoDto estadoDto){
        return ResponseEntity.ok(estadoService.update(id, estadoDto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id){
        estadoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
