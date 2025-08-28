package gp.moto.challenge_api.controller;

import gp.moto.challenge_api.dto.endereco.EnderecoDto;
import gp.moto.challenge_api.dto.endereco.EnderecoProjection;
import gp.moto.challenge_api.model.Endereco;
import gp.moto.challenge_api.service.EnderecoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;


    @GetMapping
    public ResponseEntity<List<Endereco>> findAll() {
        return ResponseEntity.ok(enderecoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> findById(@PathVariable Long id) {
        return ResponseEntity.ok(enderecoService.findById(id));
    }

    // TODO: Terminar método de salvar
    @PostMapping()
    public ResponseEntity<Endereco> save(@RequestBody EnderecoDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> update(@RequestBody EnderecoDto dto, @PathVariable Long id){
        return ResponseEntity.ok(enderecoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        enderecoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
