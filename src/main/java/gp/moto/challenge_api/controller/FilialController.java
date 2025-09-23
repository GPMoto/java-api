package gp.moto.challenge_api.controller;

import gp.moto.challenge_api.dto.filial.FilialDTO;
import gp.moto.challenge_api.model.Filial;
import gp.moto.challenge_api.model.SecaoFilial;
import gp.moto.challenge_api.service.FilialCachingService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/filial")
public class FilialController {

    @Autowired
    private FilialCachingService filialService;

    @GetMapping
    public ResponseEntity<List<Filial>> findAll() {
        return ResponseEntity.ok(filialService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filial> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(filialService.buscarPorId(id));
    }

    @GetMapping("/{id}/secao")
    public ResponseEntity<List<SecaoFilial>> findAllSecaoById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(filialService.buscarSecaoFilialPorId(id));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Filial>> findAllPage(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(filialService.paginarFilial(page, size));
    }

    @PostMapping
    public ResponseEntity<Filial> post(@RequestBody FilialDTO filialDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(filialService.criar(filialDTO));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Filial> put(@RequestBody FilialDTO filialDTO, @PathVariable Long id) {
        return ResponseEntity.ok(filialService.alterar(id, filialDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        filialService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
