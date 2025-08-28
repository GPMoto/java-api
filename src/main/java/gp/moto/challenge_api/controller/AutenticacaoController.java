package gp.moto.challenge_api.controller;


import gp.moto.challenge_api.model.Usuario;
import gp.moto.challenge_api.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("api/autenticacao")
public class AutenticacaoController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public String gerarTokenValido (@RequestParam String username, @RequestParam String password) {
        try {

            var auth = new UsernamePasswordAuthenticationToken(username, password);

            authenticationManager.authenticate(auth);

            return jwtUtil.construirToken(username);
        }catch (Exception e){
            return "Usuário ou senha inválidos";
        }
    }

    @PostMapping("/login/view")
    public ModelAndView gerarTokenValidoView (@RequestParam String username, @RequestParam String password) {
        ModelAndView mv = new ModelAndView("home/home");
        try {

            var auth = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(auth);
            String token = jwtUtil.construirToken(username);

            mv.addObject("token", token);


        }catch (Exception e){
            mv.setViewName("login/login");
            mv.addObject("errorMsg", "Usuário ou senha inválidos");
            return mv;
        }
    }

}
