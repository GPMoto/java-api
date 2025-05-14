package gp.moto.challenge_api.controller;

import gp.moto.challenge_api.dto.usuario.UsuarioDto;
import gp.moto.challenge_api.model.Usuario;
import gp.moto.challenge_api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<List<Usuario>> findAll(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Usuario>> findAllPage(@RequestParam(value = "quantidade", defaultValue = "10") Integer size, @RequestParam(value = "pagina", defaultValue = "0") Integer page){
        return ResponseEntity.ok(usuarioService.findAllPage(page, size));
    }

    @GetMapping("/filial/{idFilial}")
    public ResponseEntity<List<Usuario>> findAllByFilial(@PathVariable Long idFilial){
        return ResponseEntity.ok(usuarioService.findAllByFilial(idFilial));
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody UsuarioDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody UsuarioDto dto){
        return ResponseEntity.ok(usuarioService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
