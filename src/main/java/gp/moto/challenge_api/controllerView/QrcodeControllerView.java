package gp.moto.challenge_api.controllerView;

import gp.moto.challenge_api.dto.qrcode.QrcodeDTO;
import gp.moto.challenge_api.model.Qrcode;
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
@RequestMapping("/view/qrcode")
public class QrcodeControllerView {



    @Autowired
    private FilialCachingService filialService;

    @Autowired
    private QrcodeCachingService QrcodeService;

    @Autowired
    private MotoCachingService motoService;



    @GetMapping("/{id}")
    public ModelAndView viewQrcodes(@PathVariable("id") Long id){

        ModelAndView mv = new ModelAndView("qrcode/ver");

        try{
            mv.addObject("filial", filialService.buscarPorId(id));
            mv.addObject("qrcodes", QrcodeService.findAllByFilialPage(10,0,id));
            return mv;
        }catch(Exception e){

            return new ModelAndView("redirect:/login/index");
        }
    }

    @GetMapping("/novo/{id}")
    public ModelAndView viewCriarQrcodes(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("qrcode/novo");

        try{
            mv.addObject("qrcode", new Qrcode());
            mv.addObject("filial", filialService.buscarPorId(id));
            mv.addObject("motos", motoService.listarTodasPaginadasFilial(id, 0, 10));
            mv.addObject("idFilial", id);
            return mv;

        }catch(Exception e){
            return new ModelAndView("redirect:/login/index");
        }
    }

    @PostMapping("/novo/{id}")
    public ModelAndView criarQrcode(@Valid QrcodeDTO qrcodeDTO, BindingResult result, @PathVariable("id") Long id) {
        try {

            if (result.hasErrors()) {
                ModelAndView mv = new ModelAndView("qrcode/novo");
                mv.addObject("qrcode", new Qrcode());
                mv.addObject("filial", filialService.buscarPorId(id));
                mv.addObject("motos", motoService.listarTodasPaginadasFilial(id, 0, 10));
                mv.addObject("idFilial", id);
                return mv;
            }

            QrcodeDTO qrcodeDTOComFilial = new QrcodeDTO(
                qrcodeDTO.vlQrcode(),
                qrcodeDTO.idMoto(),
                id  
            );

            QrcodeService.save(qrcodeDTOComFilial);
            

            return new ModelAndView("redirect:/view/qrcode/" + id);
            
        } catch (Exception e) {

            return new ModelAndView("redirect:/view/qrcode/novo/" + id + "?erro=true");
        }
    }



    @GetMapping("/editar/{id}")
    public ModelAndView viewQrcodeAtualizar(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("qrcode/atualiza");
        
        try{
            mv.addObject("qrcode", QrcodeService.findById(id));
            mv.addObject("motos", motoService.listarTodasPaginadasFilial(QrcodeService.findById(id).getFilial().getIdFilial(),0,10));
            mv.addObject("idFilial", QrcodeService.findById(id).getFilial().getIdFilial());
            return mv;
        }catch(Exception e){
            return new ModelAndView("redirect:/view/qrcode/" + id);
        }
    }

    @PostMapping("/editar/{id}")
    public ModelAndView editarQrcode(@Valid QrcodeDTO qrcodeDTO, @PathVariable("id") Long id) {

        try {
            QrcodeService.update(id, qrcodeDTO);

            Qrcode qrcodeAtualizado = QrcodeService.findById(id);
            Long idFilial =  qrcodeAtualizado.getIdMoto().getIdSecaoFilial().getIdFilial().getIdFilial() ;
            
            return new ModelAndView("redirect:/view/qrcode/" + idFilial);
            
        } catch (Exception e) {

            ModelAndView mv = new ModelAndView("redirect:/view/qrcode/editar/" + id + "?erro=true");
            return mv;
        }
    }

}
