package gp.moto.challenge_api.controller;

import gp.moto.challenge_api.dto.moto.MotoDTO;
import gp.moto.challenge_api.dto.moto.MotoProjection;
import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.service.MotoCachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/moto")
public class MotoController {

    @Autowired
    private MotoCachingService motoService;

    @GetMapping
    public ResponseEntity<List<Moto>> findAll() {
        return ResponseEntity.ok(motoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoProjection> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(motoService.buscarPorIdProjection(id));
    }

    @GetMapping("/filial/{idFilial}/paginados/")
    public ResponseEntity<Page<MotoProjection>> getPageMotos(@PathVariable Long idFilial, @RequestParam(defaultValue = "0") Integer pagina, @RequestParam(defaultValue = "10") Integer quantidade) {
        return ResponseEntity.ok(motoService.listarTodasPaginadas(idFilial, pagina, quantidade));
    }

    @PostMapping
    public ResponseEntity<Moto> post(@RequestBody MotoDTO motoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(motoService.criar(motoDTO));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Moto> put(@RequestBody MotoDTO motoDTO, @PathVariable Long id) {
        return ResponseEntity.ok(motoService.alterar(id, motoDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        motoService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
