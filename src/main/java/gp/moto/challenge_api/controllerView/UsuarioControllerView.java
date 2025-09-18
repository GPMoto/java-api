package gp.moto.challenge_api.controllerView;


import gp.moto.challenge_api.dto.usuario.UsuarioDto;
import gp.moto.challenge_api.model.Usuario;
import gp.moto.challenge_api.service.PerfilService;
import gp.moto.challenge_api.service.UsuarioService;
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
@RequestMapping("/view/usuario")
public class UsuarioControllerView {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilService perfilService;


    @GetMapping("/novo/{idFilial}")
    public ModelAndView novoUsuarioView(@PathVariable Long idFilial) {

        ModelAndView mv = new ModelAndView("usuario/novo");

        mv.addObject("usuario", new UsuarioDto(null, null, null, idFilial, null));
        mv.addObject("idFilial", idFilial);
        mv.addObject("perfis", perfilService.findAll());
        return mv;
    }

    @PostMapping("/novo/{idFilial}")
    public ModelAndView criarNovoUsuario(@PathVariable Long idFilial, @Valid UsuarioDto usuarioDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("usuario/novo");
            mv.addObject("usuario", usuarioDto);
            mv.addObject("idFilial", idFilial);
            mv.addObject("perfis", perfilService.findAll());
            mv.addObject("errors", bindingResult.getAllErrors());
            return mv;
        }
        
        try {

            ModelAndView mv = new ModelAndView("redirect:/view/filial/" + idFilial);

            usuarioService.save(usuarioDto);

            return mv;

        } catch (Exception e) {
            ModelAndView mv = new ModelAndView("usuario/novo");
            mv.addObject("usuario", new UsuarioDto(null, null, null, idFilial, null));
            mv.addObject("idFilial", idFilial);
            mv.addObject("perfis", perfilService.findAll());
            String mensagemErro;
            if (e.getMessage().contains("Unique index or primary key violation") && e.getMessage().contains("NM_EMAIL")) {
                mensagemErro = "Este email já está sendo usado por outro usuário. Tente um email diferente.";
            } else if (e.getMessage().contains("constraint")) {
                mensagemErro = "Dados inválidos. Verifique as informações preenchidas.";
            } else {
                mensagemErro = "Erro interno do sistema. Tente novamente mais tarde.";
            }
            
            bindingResult.reject("error.global", mensagemErro);
            mv.addObject("errors", bindingResult.getAllErrors());
            return mv;
        }
    }

    @GetMapping("/editar/{id}")
    public  ModelAndView editarUsuarioView(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("usuario/atualiza");

        Usuario user = usuarioService.findById(id);

        mv.addObject("usuario", new UsuarioDto(user.getNmUsuario(),user.getNmEmail(),user.getSenha(),user.getIdFilial().getIdFilial(),user.getIdPerfil().getIdPerfil()));
        mv.addObject("idUsuario", user.getIdUsuario());
        mv.addObject("idFilial", user.getIdFilial().getIdFilial());
        mv.addObject("perfis", perfilService.findAll());
        return mv;
    }

    @PostMapping("/editar/{id}")
    public ModelAndView editarUsuario(@PathVariable Long id, @Valid UsuarioDto usuarioDto,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("usuario/atualiza");
            mv.addObject("usuario", usuarioDto);
            mv.addObject("idUsuario", id);
            mv.addObject("idFilial", usuarioDto.idFilial());
            mv.addObject("perfis", perfilService.findAll());
            mv.addObject("errors", bindingResult.getAllErrors());
            return mv;
        }
        try {
            ModelAndView mv = new ModelAndView("redirect:/view/filial/" + usuarioDto.idFilial());
            usuarioService.update(id, usuarioDto);

            return mv;

        }catch (Exception e) {
            Usuario user =  usuarioService.findById(id);
            ModelAndView mv = new ModelAndView("usuario/atualiza");
            mv.addObject("usuario", user);
            mv.addObject("idFilial", usuarioDto.idFilial());
            mv.addObject("perfis", perfilService.findAll());
            return mv;
        }
    }

}
