package gp.moto.challenge_api.controllerView;

import gp.moto.challenge_api.dto.uwb.UwbDTO;
import gp.moto.challenge_api.model.Uwb;
import gp.moto.challenge_api.service.*;
import jakarta.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/view/uwb")
public class UwbControllerView {



    @Autowired
    private FilialCachingService filialService;

    @Autowired
    private UwbCachingService UwbService;

    @Autowired
    private MotoCachingService motoService;



    @GetMapping("/{id}")
    public ModelAndView viewUwbs(@PathVariable("id") Long id){

        ModelAndView mv = new ModelAndView("uwb/ver");

        try{
            mv.addObject("filial", filialService.buscarPorId(id));
            mv.addObject("uwbs", UwbService.findAllByFilialPage(10,0,id));
            return mv;
        }catch(Exception e){

            return new ModelAndView("redirect:/login/index");
        }
    }

    @GetMapping("/novo/{id}")
    public ModelAndView viewCriarUwbs(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("uwb/novo");

        try{
            mv.addObject("uwb", new Uwb());
            mv.addObject("filial", filialService.buscarPorId(id));
            mv.addObject("motos", motoService.listarTodasPaginadasFilial(id, 0, 10));
            mv.addObject("idFilial", id);
            return mv;

        }catch(Exception e){
            return new ModelAndView("redirect:/login/index");
        }
    }

    @PostMapping("/novo/{id}")
    public ModelAndView criarUwb(@Valid UwbDTO uwbDTO, BindingResult result, @PathVariable("id") Long id) {
        try {

            if (result.hasErrors()) {
                ModelAndView mv = new ModelAndView("uwb/novo");
                mv.addObject("uwb", new Uwb());
                mv.addObject("filial", filialService.buscarPorId(id));
                mv.addObject("motos", motoService.listarTodasPaginadasFilial(id, 0, 10));
                mv.addObject("idFilial", id);
                return mv;
            }
            

            UwbService.save(uwbDTO);
            

            return new ModelAndView("redirect:/view/uwb/" + id);
            
        } catch (Exception e) {

            return new ModelAndView("redirect:/view/uwb/novo/" + id + "?erro=true");
        }
    }



    @GetMapping("/editar/{id}")
    public ModelAndView viewUwbAtualizar(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("uwb/atualiza");
        
        try{
            mv.addObject("uwb", UwbService.findById(id));
            mv.addObject("motos", motoService.listarTodasPaginadasFilial(UwbService.findById(id).getIdMoto().getIdSecaoFilial().getIdFilial().getIdFilial(),0,10));
            mv.addObject("idFilial", UwbService.findById(id).getIdMoto().getIdSecaoFilial().getIdFilial().getIdFilial()); 
            return mv;
        }catch(Exception e){
            return new ModelAndView("redirect:/view/uwb/view/" + id);
        }
    }

    @PostMapping("/editar/{id}")
    public ModelAndView editarUwb(@Valid UwbDTO uwbDTO, @PathVariable("id") Long id) {

        try {
            UwbService.update(id, uwbDTO);

            Uwb uwbAtualizado = UwbService.findById(id);
            Long idFilial =  uwbAtualizado.getIdMoto().getIdSecaoFilial().getIdFilial().getIdFilial() ;
            
            return new ModelAndView("redirect:/view/uwb/" + idFilial);
            
        } catch (Exception e) {

            ModelAndView mv = new ModelAndView("redirect:/view/uwb/editar/" + id + "?erro=true");
            return mv;
        }
    }

}
