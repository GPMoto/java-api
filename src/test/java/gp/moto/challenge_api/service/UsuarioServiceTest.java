package gp.moto.challenge_api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import gp.moto.challenge_api.dto.usuario.UsuarioDto;
import gp.moto.challenge_api.dto.usuario.UsuarioMapper;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.Filial;
import gp.moto.challenge_api.model.LanguageEnumPreferences;
import gp.moto.challenge_api.model.Perfil;
import gp.moto.challenge_api.model.Usuario;
import gp.moto.challenge_api.repository.UsuarioRepository;
import gp.moto.challenge_api.service.UsuarioService;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do UsuarioService")
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    private UsuarioDto usuarioDto;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        // DTO de entrada
        usuarioDto = new UsuarioDto(
            "João Silva",
            "joao@email.com",
            "senha123",
            1L,
            1L
        );

        // Entity que será criada
        usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNmUsuario("João Silva");
        usuario.setNmEmail("joao@email.com");
        usuario.setSenha("senha123");

        Filial filial = new Filial();
        filial.setIdFilial(1L);
        usuario.setIdFilial(filial);

        Perfil perfil = new Perfil();
        perfil.setIdPerfil(1L);
        usuario.setIdPerfil(perfil);
    }

    @Test
    @DisplayName("1. Deve criar usuário com idioma padrão PTBR")
    void deveCriarUsuarioComIdiomaPadraoPTBR() {
        // Arrange
        when(usuarioMapper.toEntity(usuarioDto)).thenReturn(usuario);
        when(passwordEncoder.encode("senha123")).thenReturn("senhaEncriptada");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        // Act
        Usuario resultado = usuarioService.save(usuarioDto);

        // Assert
        assertNotNull(resultado);
        assertEquals(LanguageEnumPreferences.PTBR, resultado.getLanguageEnumPreference());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("2. Deve encriptar a senha ao criar usuário")
    void deveEncriptarSenhaAoCriarUsuario() {
        // Arrange
        String senhaOriginal = "senha123";
        String senhaEncriptada = "$2a$10$encodedPassword";

        when(usuarioMapper.toEntity(usuarioDto)).thenReturn(usuario);
        when(passwordEncoder.encode(senhaOriginal)).thenReturn(senhaEncriptada);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        // Act
        Usuario resultado = usuarioService.save(usuarioDto);

        // Assert
        verify(passwordEncoder, times(1)).encode(senhaOriginal);
        verify(usuarioRepository).save(argThat(user ->
            senhaEncriptada.equals(user.getSenha())
        ));
    }

    @Test
    @DisplayName("3. Deve buscar usuário por ID com sucesso")
    void deveBuscarUsuarioPorIdComSucesso() {
        // Arrange
        Long usuarioId = 1L;
        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));

        // Act
        Usuario resultado = usuarioService.findById(usuarioId);

        // Assert
        assertNotNull(resultado);
        assertEquals(usuarioId, resultado.getIdUsuario());
        assertEquals("João Silva", resultado.getNmUsuario());
        verify(usuarioRepository, times(1)).findById(usuarioId);
    }

    @Test
    @DisplayName("4. Deve lançar exceção quando usuário não for encontrado")
    void deveLancarExcecaoQuandoUsuarioNaoForEncontrado() {
        // Arrange
        Long usuarioIdInexistente = 999L;
        when(usuarioRepository.findById(usuarioIdInexistente))
            .thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> usuarioService.findById(usuarioIdInexistente)
        );

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(usuarioRepository, times(1)).findById(usuarioIdInexistente);
    }

    @Test
    @DisplayName("5. Deve atualizar usuário mantendo o idioma existente")
    void deveAtualizarUsuarioMantendoIdiomaExistente() {
        // Arrange
        Long usuarioId = 1L;
        LanguageEnumPreferences idiomaOriginal = LanguageEnumPreferences.EN;
        usuario.setLanguageEnumPreference(idiomaOriginal);

        UsuarioDto dtoAtualizado = new UsuarioDto(
            "João Silva Atualizado",
            "joao.novo@email.com",
            "novaSenha123",
            1L,
            1L
        );

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        doNothing().when(usuarioMapper).updateEntityFromDto(any(), any());

        // Act
        Usuario resultado = usuarioService.update(usuarioId, dtoAtualizado);

        // Assert
        assertNotNull(resultado);
        // O idioma deve ser mantido pois não vem no DTO
        assertEquals(idiomaOriginal, resultado.getLanguageEnumPreference());
        verify(usuarioMapper, times(1)).updateEntityFromDto(dtoAtualizado, usuario);
        verify(usuarioRepository, times(1)).save(usuario);
    }
}
