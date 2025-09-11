package gp.moto.challenge_api.controllerView;


import gp.moto.challenge_api.dto.login.LoginDTO;
import gp.moto.challenge_api.model.Usuario;
import gp.moto.challenge_api.repository.MotoRepository;
import gp.moto.challenge_api.repository.UsuarioRepository;
import gp.moto.challenge_api.security.JWTUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

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

    @PostMapping
    public ModelAndView gerarTokenValidoView(@Valid LoginDTO loginDTO) {
        System.out.println("Tentativa de login para usuário: " + loginDTO.getUsername());
        ModelAndView mv = new ModelAndView("/home/home");
        try {

            var auth = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
            authenticationManager.authenticate(auth);

            String token = jwtUtil.construirToken(loginDTO.getUsername());
            Optional<Usuario> user = userRep.findByNmUsuario(loginDTO.getUsername());


            Long idFilial = user.get().getIdFilial().getIdFilial();

            mv.addObject("token", token);
            Pageable pageable = PageRequest.of(0, 10);
            mv.addObject("motos", motoRep.findAllByFilial(pageable, user.get().getIdFilial().getIdFilial()));
            mv.addObject("idFilial", idFilial);

            return mv;

        } catch (Exception e) {
            System.out.println("Falha no login para usuário: " + loginDTO.getUsername() + ". Motivo: " + e.getMessage());
            mv.setViewName("redirect:/login/index");
            mv.addObject("errorMsg", "Usuário ou senha inválidos");
            return mv;
        }
    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/home/home");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        var user = userRep.findByNmUsuario(username);
        if (user.isPresent()) {
            Long idFilial = user.get().getIdFilial().getIdFilial();

            Pageable pageable = PageRequest.of(0, 10);
            mv.addObject("motos", motoRep.findAllByFilial(pageable, user.get().getIdFilial().getIdFilial()));
            mv.addObject("usuario", user.get());
            mv.addObject("idFilial", idFilial);

        }
        return mv;
    }


    @GetMapping("/acesso_negado")
    public ModelAndView acessoNegado() {
        ModelAndView mv = new ModelAndView("error/acesso_negado");
        
        // Pegar dados do usuário logado se disponível
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
                String username = auth.getName();
                var user = userRep.findByNmUsuario(username);
                if (user.isPresent()) {
                    mv.addObject("usuario", user.get());
                    mv.addObject("idFilial", user.get().getIdFilial().getIdFilial());
                }
            }
        } catch (Exception e) {
            return mv;
        }
        
        mv.addObject("titulo", "Acesso Negado");
        mv.addObject("mensagem", "Você não tem permissão para acessar esta página ou realizar esta operação.");
        mv.addObject("submensagem", "Entre em contato com o administrador do sistema se acredita que deveria ter acesso.");
        
        return mv;
    }


    @GetMapping
    public ModelAndView loginView() {
        ModelAndView mv = new ModelAndView("login/index");
        return mv;
    }
}
