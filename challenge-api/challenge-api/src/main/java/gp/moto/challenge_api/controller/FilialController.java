package gp.moto.challenge_api.controller;

import gp.moto.challenge_api.dto.FilialDTO;
import gp.moto.challenge_api.service.FilialCachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/filial")
public class FilialController {

    @Autowired
    private FilialCachingService filialService;

    @GetMapping
    public List<FilialDTO> get(){
        return filialService.listarTodos();
    }

    @PostMapping
    public void post(@RequestBody FilialDTO filialDTO){
        filialService.criar(filialDTO);
    }

    @PutMapping(value = "/{id}")
    public FilialDTO put(@RequestBody FilialDTO filialDTO, @PathVariable Long id){
        return filialService.alterar(filialDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        filialService.deletar(id);
    }

}
