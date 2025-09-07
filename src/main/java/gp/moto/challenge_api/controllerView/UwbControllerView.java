package gp.moto.challenge_api.controllerView;


import gp.moto.challenge_api.dto.contato.ContatoDTO;
import gp.moto.challenge_api.dto.endereco.EnderecoDto;
import gp.moto.challenge_api.dto.filial.FilialDTO;
import gp.moto.challenge_api.dto.filial.FilialFormDTO;
import gp.moto.challenge_api.dto.telefone.TelefoneDTO;
import gp.moto.challenge_api.dto.uwb.UwbDTO;
import gp.moto.challenge_api.model.Filial;
import gp.moto.challenge_api.model.Uwb;
import gp.moto.challenge_api.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ContatoCachingService contatoService;

    @Autowired
    private TelefoneCachingService telefoneService;



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

    @GetMapping("/editar/{id}")
    public ModelAndView viewUwbAtualizar(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("uwb/atualiza");
        
        try{
            mv.addObject("uwb", UwbService.findById(id));
            mv.addObject("motos", motoService.listarTodasPaginadasFilial(UwbService.findById(id).getIdMoto().getIdSecaoFilial().getIdFilial().getIdFilial(),0,10));
            mv.addObject("idFilial", UwbService.findById(id).getIdMoto() != null ? 
                        UwbService.findById(id).getIdMoto().getIdSecaoFilial().getIdFilial().getIdFilial() : 1L); 
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
            Long idFilial = uwbAtualizado.getIdMoto() != null ? 
                        uwbAtualizado.getIdMoto().getIdSecaoFilial().getIdFilial().getIdFilial() : 1L;
            
            return new ModelAndView("redirect:/view/uwb/" + idFilial);
            
        } catch (Exception e) {
            return new ModelAndView("redirect:/view/uwb/editar/" + id + "?erro=true");
        }
    }

}
