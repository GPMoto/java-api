package gp.moto.challenge_api.controller;

import gp.moto.challenge_api.dto.ContatoDTO;
import gp.moto.challenge_api.service.ContatoCachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/contato")
public class ContatoController {

    @Autowired
    private ContatoCachingService contatoService;

    @GetMapping
    public List<ContatoDTO> get(){
        return contatoService.listarTodos();
    }

    @PostMapping
    public void post(@RequestBody ContatoDTO contatoDTO){
        contatoService.criar(contatoDTO);
    }

    @PutMapping(value = "/{id}")
    public ContatoDTO put(@RequestBody ContatoDTO contatoDTO, @PathVariable Long id){
        return contatoService.alterar(contatoDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
         contatoService.deletar(id);
    }



}
