package gp.moto.challenge_api.controller;

import gp.moto.challenge_api.dto.contato.ContatoDTO;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Contato;
import gp.moto.challenge_api.service.ContatoCachingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/contato")
public class ContatoController {

    @Autowired
    private ContatoCachingService contatoService;


    @GetMapping
    public ResponseEntity<List<Contato>> findAll() {
        return ResponseEntity.ok(contatoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> findById(@PathVariable Long id) {
        return ResponseEntity.ok(contatoService.buscarPorId(id));
    }


    @PostMapping
    public ResponseEntity<Contato> post(@RequestBody @Valid ContatoDTO contatoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.criar(contatoDTO));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Contato> put(@RequestBody ContatoDTO contatoDTO, @PathVariable Long id) {
        return ResponseEntity.ok(contatoService.alterar(id, contatoDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contatoService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
