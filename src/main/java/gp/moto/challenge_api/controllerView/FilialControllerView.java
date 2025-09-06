package gp.moto.challenge_api.controllerView;


import gp.moto.challenge_api.dto.moto.MotoDTO;
import gp.moto.challenge_api.model.Moto;
import gp.moto.challenge_api.service.FilialCachingService;
import gp.moto.challenge_api.service.MotoCachingService;
import gp.moto.challenge_api.service.SecaoFilialService;
import gp.moto.challenge_api.service.TipoMotoService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/view/filial")
public class FilialControllerView {

    @Autowired
    private FilialCachingService filialService;

    @Autowired
    private MotoCachingService motoService;

    @Autowired
    private TipoMotoService tipoMotoService;

    @Autowired
    private SecaoFilialService secaoFilialService;


    @GetMapping("/{id}")
    public ModelAndView viewFilial(@PathVariable("id") Long id){

        ModelAndView mv = new ModelAndView("/filial/ver");

        try{
            mv.addObject("filial", filialService.buscarPorId(id));
            return mv;
        }catch(Exception e){

            return new ModelAndView("redirect:/login/index");
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
    public ModelAndView salvarMoto(@Valid MotoDTO motoDTO) {
        ModelAndView mv = new ModelAndView("redirect:/login/index");
        try{
            
            motoService.criar(motoDTO);
            return mv;
        }catch(Exception e){
            return mv;
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
