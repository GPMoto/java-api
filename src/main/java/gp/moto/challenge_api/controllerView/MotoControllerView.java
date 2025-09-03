package gp.moto.challenge_api.controllerView;


import gp.moto.challenge_api.dto.moto.MotoDTO;
import gp.moto.challenge_api.dto.moto.MotoMapper;
import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.service.MotoCachingService;
import gp.moto.challenge_api.service.SecaoFilialService;
import gp.moto.challenge_api.service.TipoMotoService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @PostMapping
    public ModelAndView salvarMoto(@Valid MotoDTO motoDTO) {
        ModelAndView mv = new ModelAndView("redirect:/login/index");
        try{
            
            motoService.criar(motoDTO);
            return mv;
        }catch(Exception e){
            return mv;
        }
    }
}
