package gp.moto.challenge_api.controllerView;


import gp.moto.challenge_api.service.MotoCachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/view/moto")
public class MotoControllerView {

    @Autowired
    private MotoCachingService motoService;

    @GetMapping("/view/nova/moto")
    public ModelAndView viewMoto(@RequestParam(value = "id") Long id){

        ModelAndView mv = new ModelAndView("/moto/nova");

        try{

            return mv;
        }catch(Exception e){


            return mv;
        }

    }
}
