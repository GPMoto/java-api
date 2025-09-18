package gp.moto.challenge_api.controllerView;


import gp.moto.challenge_api.dto.moto.MotoDTO;
import gp.moto.challenge_api.dto.moto.MotoMapper;
import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.repository.MotoRepository;
import gp.moto.challenge_api.service.MotoCachingService;
import gp.moto.challenge_api.service.SecaoFilialService;
import gp.moto.challenge_api.service.TipoMotoService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/view/moto")
public class MotoControllerView {

    @Autowired
    private MotoCachingService motoService;

    @Autowired
    private TipoMotoService tipoMotoService;

    @Autowired
    private SecaoFilialService secaoFilialService;

    @Autowired
    private MotoRepository motoRep;


    @GetMapping("/nova")
    public ModelAndView viewMoto(){

        ModelAndView mv = new ModelAndView("moto/nova");

        try{
            mv.addObject("moto", new Moto());

            mv.addObject("tiposMoto", tipoMotoService.findAll());
            mv.addObject("secoesFilial", secaoFilialService.findAll());

            return mv;
        }catch(Exception e){


            return mv;
        }

    }

    @GetMapping("/editar/{id}")
    public ModelAndView viewMotoAtualizar(@PathVariable("id") Long id){

        ModelAndView mv = new ModelAndView("moto/atualiza");

        try{
            mv.addObject("moto", motoService.buscarPorId(id));

            mv.addObject("tiposMoto", tipoMotoService.findAll());
            mv.addObject("secoesFilial", secaoFilialService.findAll());

            return mv;
        }catch(Exception e){


            return mv;
        }

    }


    @PostMapping
    public ModelAndView salvarMoto(@Valid MotoDTO motoDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("moto/nova");
            mv.addObject("moto", motoDTO);
            mv.addObject("tiposMoto", tipoMotoService.findAll());
            mv.addObject("secoesFilial", secaoFilialService.findAll());
            mv.addObject("errors", bindingResult.getAllErrors());
            return mv;
        }

        try{
            ModelAndView mv = new ModelAndView("redirect:/login/index");
            motoService.criar(motoDTO);
            return mv;
        }catch(Exception e){
            ModelAndView mvMoto = new ModelAndView("moto/nova?erro=true");
            mvMoto.addObject("moto", motoDTO);
            mvMoto.addObject("tiposMoto", tipoMotoService.findAll()); 
            mvMoto.addObject("secoesFilial", secaoFilialService.findAll()); 
            mvMoto.addObject("errorMessage", "Erro ao salvar moto: " + e.getMessage()); 
            return mvMoto;
        }
    }

    @PostMapping("{id}")
    public ModelAndView editarMoto(@Valid MotoDTO motoDTO, @PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("redirect:/login/index");
        try{

            motoService.alterar(id,motoDTO);
            return mv;
        }catch(Exception e){
            return mv;
        }
    }

    @GetMapping("remover/{id}")
    public ModelAndView deleteMoto(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("redirect:/login/index");
        try{

            motoService.deletar(id);
            return mv;
        }catch(Exception e){
            return mv;
        }
    }

}
