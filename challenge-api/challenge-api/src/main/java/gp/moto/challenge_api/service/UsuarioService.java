package gp.moto.challenge_api.service;

import gp.moto.challenge_api.dto.usuario.UsuarioDto;
import gp.moto.challenge_api.dto.usuario.UsuarioMapper;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Usuario;
import gp.moto.challenge_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Page<Usuario> findAllPage(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return usuarioRepository.findAll(pageable);
    }

    public Usuario save(UsuarioDto dto) {
        return usuarioRepository.save(usuarioMapper.toEntity(dto));
    }

    public Usuario update(Long id, UsuarioDto dto) {
        Usuario usuario = findById(id);
        usuarioMapper.updateEntityFromDto(dto, usuario);
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    public List<Usuario> findAllByFilial(Long idFilial) {
        return usuarioRepository.findAllByFilial(idFilial);
    }

    public boolean delete(Long id) {
        Usuario usuario = findById(id);
        usuarioRepository.delete(usuario);
        return true;
    }
}
