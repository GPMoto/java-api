package gp.moto.challenge_api.controller;

import gp.moto.challenge_api.dto.secaoFilial.SecaoFilialDto;
import gp.moto.challenge_api.model.SecaoFilial;
import gp.moto.challenge_api.service.SecaoFilialService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secao-filial")
public class SecaoFilialController {

    @Autowired
    private SecaoFilialService secaoFilialService;



    @GetMapping
    public ResponseEntity<List<SecaoFilial>> findAll(){
        return ResponseEntity.ok(secaoFilialService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SecaoFilial> findById(@PathVariable Long id){
        return ResponseEntity.ok(secaoFilialService.findById(id));
    }

    @GetMapping("/filial/{idFilial}")
    public ResponseEntity<List<SecaoFilial>> findAllByFilial(@PathVariable Long idFilial){
        return ResponseEntity.ok(secaoFilialService.findAllByFilial(idFilial));
    }

    @PostMapping
    public ResponseEntity<SecaoFilial> save(@RequestBody SecaoFilialDto dto){
        return ResponseEntity.ok(secaoFilialService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SecaoFilial> update(@PathVariable Long id, @RequestBody SecaoFilialDto dto){
        return ResponseEntity.ok(secaoFilialService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        secaoFilialService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
