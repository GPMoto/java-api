package gp.moto.challenge_api.service;

import com.github.flanchanowo.response.enums.Status;
import gp.moto.challenge_api.dto.usuario.UsuarioDto;
import gp.moto.challenge_api.dto.usuario.UsuarioMapper;
import gp.moto.challenge_api.exception.InvalidTokenException;
import gp.moto.challenge_api.exception.ResourceNotFoundException;
import gp.moto.challenge_api.model.ExpoPushTokenUser;
import gp.moto.challenge_api.model.LanguageEnumPreferences;
import gp.moto.challenge_api.model.PerfilEnum;
import gp.moto.challenge_api.model.Usuario;
import gp.moto.challenge_api.repository.UsuarioRepository;
import gp.moto.challenge_api.security.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PushNotificationService pushNotificationService;

    @Autowired
    private MessageSource messageSource;

    @Transactional(readOnly = true)
    public void sendNotificationToAdmins(
        Long filialId,
        String titleKey,
        String messageKey,
        LinkedHashMap<String, List<Object>> additionalParameters
    ) {
        List<Usuario> usuarios = usuarioRepository.findByPerfilAndFilial(
            PerfilEnum.ADMINISTRADOR.toString(),
            filialId
        );

        List<Optional<Map<String, Status>>> results = usuarios
            .stream()
            .map(user -> {
                Locale locale = getLocaleFromLanguageEnum(
                    user.getLanguageEnumPreference()
                );

                Object[] titleParams = additionalParameters.containsKey(
                        "titleParameters"
                    )
                    ? additionalParameters.get("titleParameters").toArray()
                    : new Object[0];

                Object[] messageParams = additionalParameters.containsKey(
                        "messageParameters"
                    )
                    ? additionalParameters.get("messageParameters").toArray()
                    : new Object[0];

                String translatedTitle = messageSource.getMessage(
                    titleKey,
                    titleParams,
                    locale
                );

                log.info("title: {}", translatedTitle);

                String translatedMessage = messageSource.getMessage(
                    messageKey,
                    messageParams,
                    locale
                );

                log.info("message: {}", translatedMessage);

                log.info("tokens do user: {}", user.getExpoPushTokenUsers());

                return pushNotificationService.sendNotification(
                    user.getExpoPushTokenUsers(),
                    translatedTitle,
                    translatedMessage
                );
            })
            .collect(Collectors.toList());

        results.forEach(result -> {
            result.ifPresent(status -> {
                log.info("Notification sent to user: {}", status);
            });
        });
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findAllUsuario")
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findAllPageUsuario", key = "#page + '-' + #size")
    public Page<Usuario> findAllPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return usuarioRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Usuario findByToken(HttpServletRequest request) {
        String username = JWTUtil.getNameFromRequest(request);

        if (username == null) {
            throw new InvalidTokenException("Não autenticado");
        }

        return usuarioRepository
            .findByNmUsuario(username)
            .orElseThrow(() ->
                new ResourceNotFoundException("Usuário não encontrado")
            );
    }

    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        return usuarioRepository
            .findByNmUsuario(username)
            .orElseThrow(() ->
                new ResourceNotFoundException("Usuário não encontrado")
            );
    }

    @Transactional
    public Usuario save(UsuarioDto dto) {
        String senhaDto = dto.senha();
        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setSenha(passwordEncoder.encode(senhaDto));

        // Define PTBR como padrão para novos usuários
        usuario.setLanguageEnumPreference(LanguageEnumPreferences.PTBR);

        limparCache();
        return usuarioRepository.save(usuario);
    }

    public boolean saveLanguagePreference(
        LanguageEnumPreferences language,
        HttpServletRequest request
    ) {
        Usuario usuario = findByToken(request);
        usuario.setLanguageEnumPreference(language);
        usuarioRepository.save(usuario);
        return true;
    }

    @Transactional
    public Usuario update(Long id, UsuarioDto dto) {
        Usuario usuario = findById(id);
        usuarioMapper.updateEntityFromDto(dto, usuario);
        // languageEnumPreference não é atualizado via DTO, use o endpoint /language
        limparCache();
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "findByIdUsuario", key = "#id")
    public Usuario findById(Long id) {
        return usuarioRepository
            .findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("Usuário não encontrado")
            );
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

    private Locale getLocaleFromLanguageEnum(LanguageEnumPreferences language) {
        if (language == null) {
            return new Locale("pt", "BR"); // Padrão
        }

        return switch (language) {
            case PTBR -> new Locale("pt", "BR");
            case EN -> new Locale("en", "US");
            case ES -> new Locale("es", "ES");
        };
    }

    private String getMessage(
        @NonNull String key,
        @NonNull Locale locale,
        Object... args
    ) {
        return messageSource.getMessage(key, args, locale);
    }

    @Transactional
    @CacheEvict(
        value = {
            "findAllUsuario",
            "findAllPageUsuario",
            "findByIdUsuario",
            "findAllByFilialUsuario",
        },
        allEntries = true
    )
    public void limparCache() {
        System.out.println("Limpando cache...");
    }
}
