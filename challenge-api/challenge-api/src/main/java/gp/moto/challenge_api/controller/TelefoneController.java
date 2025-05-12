package gp.moto.challenge_api.controller;


import gp.moto.challenge_api.dto.TelefoneDTO;
import gp.moto.challenge_api.service.TelefoneCachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/telefone")
public class TelefoneController {

    @Autowired
    private TelefoneCachingService telefoneCachingService;

    @GetMapping
    public List<TelefoneDTO> get(){
        return telefoneCachingService.listarTodos();
    }

    @PostMapping
    public void post(@RequestBody TelefoneDTO telefoneDTO){
        telefoneCachingService.criar(telefoneDTO);
    }

    @PutMapping(value = "/{id}")
    public TelefoneDTO put(@RequestBody TelefoneDTO telefoneDTO, @PathVariable Long id){
        return telefoneCachingService.alterar(telefoneDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        telefoneCachingService.deletar(id);
    }

}
