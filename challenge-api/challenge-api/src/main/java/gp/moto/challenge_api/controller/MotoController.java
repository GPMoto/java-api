package gp.moto.challenge_api.controller;

import gp.moto.challenge_api.dto.MotoDTO;
import gp.moto.challenge_api.service.MotoCachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/moto")
public class MotoController {

    @Autowired
    private MotoCachingService motoService;

    @GetMapping
    public List<MotoDTO> get(){
        return motoService.listarTodos();
    }

    @PostMapping
    public void post(@RequestBody MotoDTO motoDTO){
        motoService.criar(motoDTO);
    }

    @PutMapping(value = "/{id}")
    public MotoDTO put(@RequestBody MotoDTO motoDTO, @PathVariable Long id){
        return motoService.alterar(motoDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        motoService.deletar(id);
    }



}
