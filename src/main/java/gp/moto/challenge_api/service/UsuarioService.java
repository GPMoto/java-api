package gp.moto.challenge_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gp.moto.challenge_api.dto.usuario.UsuarioDto;
import gp.moto.challenge_api.dto.usuario.UsuarioMapper;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Usuario;
import gp.moto.challenge_api.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Transactional(readOnly = true)
    @Cacheable(value = "findAllUsuario")
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findAllPageUsuario", key = "#page + '-' + #size")
    public Page<Usuario> findAllPage(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return usuarioRepository.findAll(pageable);
    }

    @Transactional
    public Usuario save(UsuarioDto dto) {
        limparCache();
        return usuarioRepository.save(usuarioMapper.toEntity(dto));
    }

    @Transactional
    public Usuario update(Long id, UsuarioDto dto) {
        Usuario usuario = findById(id);
        usuarioMapper.updateEntityFromDto(dto, usuario);
        limparCache();
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findByIdUsuario", key = "#id")
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findAllByFilialUsuario", key = "#idFilial")
    public List<Usuario> findAllByFilial(Long idFilial) {
        return usuarioRepository.findAllByFilial(idFilial);
    }

    @Transactional
    public boolean delete(Long id) {
        Usuario usuario = findById(id);
        usuarioRepository.delete(usuario);
        limparCache();
        return true;
    }

    @Transactional
    @CacheEvict(value = {
        "findAllUsuario", "findAllPageUsuario", "findByIdUsuario", "findAllByFilialUsuario"
    }, allEntries = true)
    public void limparCache(){
        System.out.println("Limpando cache...");
    }
}
