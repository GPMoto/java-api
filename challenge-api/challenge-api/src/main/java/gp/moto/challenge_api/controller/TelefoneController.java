package gp.moto.challenge_api.controller;


import gp.moto.challenge_api.dto.telefone.TelefoneDTO;
import gp.moto.challenge_api.model.Telefone;
import gp.moto.challenge_api.service.TelefoneCachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/telefone")
public class TelefoneController {

    @Autowired
    private TelefoneCachingService telefoneCachingService;

    @GetMapping
    public ResponseEntity<List<Telefone>> findAll() {
        return ResponseEntity.ok(telefoneCachingService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Telefone> post(@RequestBody TelefoneDTO telefoneDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(telefoneCachingService.criar(telefoneDTO));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Telefone> put(@RequestBody TelefoneDTO telefoneDTO, @PathVariable Long id) {
        return ResponseEntity.ok(telefoneCachingService.alterar(id, telefoneDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        telefoneCachingService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
