package gp.moto.challenge_api.controllerView;

import gp.moto.challenge_api.dto.login.LoginDTO;
import gp.moto.challenge_api.model.Usuario;
import gp.moto.challenge_api.repository.MotoRepository;
import gp.moto.challenge_api.repository.UsuarioRepository;
import gp.moto.challenge_api.security.JWTUtil;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class AutenticacaoControllerView {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MotoRepository motoRep;

    @Autowired
    private UsuarioRepository userRep;

    @Autowired
    private MessageSource messageSource;

    @PostMapping
    public ModelAndView gerarTokenValidoView(
        @Valid LoginDTO loginDTO,
        BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("login/index");
            mv.addObject("loginDTO", loginDTO);
            mv.addObject("errors", bindingResult.getAllErrors());
            return mv;
        }

        ModelAndView mv = new ModelAndView("/home/home");
        try {
            var auth = new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(),
                loginDTO.getPassword()
            );
            authenticationManager.authenticate(auth);

            String token = jwtUtil.construirToken(loginDTO.getUsername());
            Optional<Usuario> user = userRep.findByNmUsuario(
                loginDTO.getUsername()
            );

            Long idFilial = user.get().getIdFilial().getIdFilial();

            mv.addObject("token", token);
            Pageable pageable = PageRequest.of(0, 10);
            mv.addObject(
                "motos",
                motoRep.findAllByFilial(
                    pageable,
                    user.get().getIdFilial().getIdFilial()
                )
            );
            mv.addObject("idFilial", idFilial);

            return mv;
        } catch (Exception e) {
            System.out.println(
                "Falha no login para usuário: " +
                    loginDTO.getUsername() +
                    ". Motivo: " +
                    e.getMessage()
            );
            mv.setViewName("redirect:/login/index");
            String errorMsg = messageSource.getMessage(
                "login.error.invalid",
                null,
                LocaleContextHolder.getLocale()
            );
            mv.addObject("errorMsg", errorMsg);
            return mv;
        }
    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("home/home");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        var user = userRep.findByNmUsuario(username);
        if (user.isPresent()) {
            Long idFilial = user.get().getIdFilial().getIdFilial();

            Pageable pageable = PageRequest.of(0, 10);
            mv.addObject(
                "motos",
                motoRep.findAllByFilial(
                    pageable,
                    user.get().getIdFilial().getIdFilial()
                )
            );
            mv.addObject("usuario", user.get());
            mv.addObject("idFilial", idFilial);
        }
        return mv;
    }

    @GetMapping("/acesso_negado")
    public ModelAndView acessoNegado() {
        ModelAndView mv = new ModelAndView("error/acesso_negado");

        try {
            Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();
            if (
                auth != null &&
                auth.isAuthenticated() &&
                !auth.getName().equals("anonymousUser")
            ) {
                String username = auth.getName();
                var user = userRep.findByNmUsuario(username);
                if (user.isPresent()) {
                    mv.addObject("usuario", user.get());
                    mv.addObject(
                        "idFilial",
                        user.get().getIdFilial().getIdFilial()
                    );
                }
            }
        } catch (Exception e) {
            return mv;
        }

        String titulo = messageSource.getMessage(
            "login.error.title",
            null,
            LocaleContextHolder.getLocale()
        );
        String mensagem = messageSource.getMessage(
            "login.error.message",
            null,
            LocaleContextHolder.getLocale()
        );
        String submensagem = messageSource.getMessage(
            "login.error.submessage",
            null,
            LocaleContextHolder.getLocale()
        );

        mv.addObject("titulo", titulo);
        mv.addObject("mensagem", mensagem);
        mv.addObject("submensagem", submensagem);

        return mv;
    }

    @GetMapping
    public ModelAndView loginView() {
        ModelAndView mv = new ModelAndView("login/index");
        return mv;
    }
}
