package gp.moto.challenge_api.controllerView;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import gp.moto.challenge_api.dto.secaoFilial.SecaoFilialDto;
import gp.moto.challenge_api.model.SecaoFilial;
import gp.moto.challenge_api.model.TipoSecao;
import gp.moto.challenge_api.service.SecaoFilialService;
import gp.moto.challenge_api.service.TipoSecaoService;
import gp.moto.challenge_api.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
@RequestMapping("/view/secao-filial")
class SecaoFilialControllerView {

    @Autowired
    private SecaoFilialService secaoFilialService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TipoSecaoService tipoSecaoService;

    @GetMapping()
    public ModelAndView getSecaoFilialIndexPage() {
        ModelAndView modelAndView = new ModelAndView("filial/secao-filial");

        Authentication auth =
            SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        var user = usuarioService.findByUsername(username);

        Long idFilial = user.getIdFilial().getIdFilial();

        List<SecaoFilial> secoes = secaoFilialService.findAllByFilial(idFilial);

        modelAndView.addObject("secoes", secoes);

        modelAndView.addObject("idFilial", idFilial);

        return modelAndView;
    }

    @GetMapping("/nova/{id}")
    public ModelAndView getAddSecaoFilialPage(@PathVariable(name = "id") Long filialId) {
        ModelAndView modelAndView = new ModelAndView("filial/add-secao-filial");

        List<TipoSecao> tiposSecao = tipoSecaoService.findAll();

        SecaoFilialDto secaoFilialDto = new SecaoFilialDto();
        secaoFilialDto.setIdFilial(filialId);

        modelAndView.addObject("tiposSecao", tiposSecao);
        modelAndView.addObject("secaoFilialDto", secaoFilialDto);
        modelAndView.addObject("filialId", filialId);

        return modelAndView;
    }

    @PostMapping("/nova/")
    public ModelAndView saveSecaoFilial(@ModelAttribute SecaoFilialDto dto) {
        secaoFilialService.save(dto);

        return new ModelAndView("redirect:/view/secao-filial");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView getEditSecaoFilialPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("filial/editar-secao-filial");

        try {
            SecaoFilial secaoFilial = secaoFilialService.findById(id);
            List<TipoSecao> tiposSecao = tipoSecaoService.findAll();

            SecaoFilialDto dto = new SecaoFilialDto();
            dto.setLado1(secaoFilial.getLado1());
            dto.setLado2(secaoFilial.getLado2());
            dto.setLado3(secaoFilial.getLado3());
            dto.setLado4(secaoFilial.getLado4());
            dto.setIdTipoSecao(secaoFilial.getIdTipoSecao().getIdTipoSecao());
            dto.setIdFilial(secaoFilial.getIdFilial().getIdFilial());

            modelAndView.addObject("tiposSecao", tiposSecao);
            modelAndView.addObject("secaoFilialDto", dto);
            modelAndView.addObject("idSecaoFilial", id);

            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("redirect:/view/secao-filial");
        }
    }

    @PostMapping("/editar/{id}")
    public ModelAndView updateSecaoFilial(@PathVariable("id") Long id, @ModelAttribute SecaoFilialDto dto) {
        ModelAndView mv = new ModelAndView("redirect:/view/secao-filial");
        try {
            secaoFilialService.update(id, dto);
            return mv;
        } catch (Exception e) {
            return mv;
        }
    }

    @GetMapping("/remover/{id}")
    public ModelAndView deleteSecaoFilial(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("redirect:/view/secao-filial");
        try {
            secaoFilialService.delete(id);
            return mv;
        } catch (Exception e) {
            return mv;
        }
    }
}
