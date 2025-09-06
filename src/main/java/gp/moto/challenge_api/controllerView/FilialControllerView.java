package gp.moto.challenge_api.controllerView;


import gp.moto.challenge_api.dto.contato.ContatoDTO;
import gp.moto.challenge_api.dto.endereco.EnderecoDto;
import gp.moto.challenge_api.dto.filial.FilialDTO;
import gp.moto.challenge_api.dto.filial.FilialFormDTO;
import gp.moto.challenge_api.dto.moto.MotoDTO;
import gp.moto.challenge_api.dto.telefone.TelefoneDTO;
import gp.moto.challenge_api.model.Contato;
import gp.moto.challenge_api.model.Endereco;
import gp.moto.challenge_api.model.Filial;
import gp.moto.challenge_api.model.Telefone;
import gp.moto.challenge_api.service.CidadeService;
import gp.moto.challenge_api.service.ContatoCachingService;
import gp.moto.challenge_api.service.EnderecoService;
import gp.moto.challenge_api.service.FilialCachingService;
import gp.moto.challenge_api.service.MotoCachingService;
import gp.moto.challenge_api.service.TelefoneCachingService;
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
    private CidadeService cidadeService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ContatoCachingService contatoService;

    @Autowired
    private TelefoneCachingService telefoneService;



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
    public ModelAndView viewFilialAtualizar(@PathVariable("id") Long id){

        ModelAndView mv = new ModelAndView("filial/atualiza");

        try{
            mv.addObject("filial", filialService.buscarPorId(id));
            mv.addObject("cidades", cidadeService.findAll());
            return mv;
        }catch(Exception e){


            return mv;
        }

    }


    @PostMapping("{id}")
    public ModelAndView editarFilial(@Valid FilialFormDTO filialFormDTO, @PathVariable("id") Long id) {
        try {

            Filial filialAtual = filialService.buscarPorId(id);
            

            if (filialAtual.getIdEndereco() != null) {
                EnderecoDto enderecoDto = new EnderecoDto(
                    filialFormDTO.nmLogradouro(),
                    filialFormDTO.nrLogradouro(),
                    filialFormDTO.idCidade(),
                    filialFormDTO.cep()
                );
                enderecoService.update(filialAtual.getIdEndereco().getIdEndereco(), enderecoDto);
            }
            

            if (filialAtual.getIdContato() != null) {
                ContatoDTO contatoDto = new ContatoDTO(
                    filialFormDTO.nmDono(),
                    filialFormDTO.status(),
                    filialAtual.getIdContato().getIdTelefone().getId_telefone()
                );
                contatoService.alterar(filialAtual.getIdContato().getIdContato(), contatoDto);
                

                if (filialAtual.getIdContato().getIdTelefone() != null) {
                    TelefoneDTO telefoneDto = new TelefoneDTO(
                        filialAtual.getIdContato().getIdTelefone().getDdi(), 
                        filialFormDTO.ddd(),
                        filialFormDTO.numero()
                    );
                    telefoneService.alterar(filialAtual.getIdContato().getIdTelefone().getId_telefone(), telefoneDto);
                }
            }
            
            FilialDTO filialDTO = new FilialDTO(
                filialFormDTO.cnpjFilial(),
                filialFormDTO.senhaFilial(),
                filialAtual.getIdEndereco().getIdEndereco(),
                filialAtual.getIdContato().getIdContato()
            );
            
            filialService.alterar(id, filialDTO);
            
            return new ModelAndView("redirect:/view/filial/" + id);
            
        } catch (Exception e) {
            return new ModelAndView("redirect:/view/filial/editar/" + id + "?erro=true");
        }
}

}
