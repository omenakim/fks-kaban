package com.fks.kanban.infrastructure.security.authorization;

import com.fks.kanban.domain.exception.UsuarioNaoEncontradoException;
import com.fks.kanban.domain.model.Usuario;
import com.fks.kanban.domain.repository.UsuarioRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Log4j2
class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<Usuario> maybeUserCredentials = usuarioRepository.findByUsername(username);
        if (maybeUserCredentials.isPresent()) {
            var usuario = maybeUserCredentials.get();
            return new AuthUser(
                    usuario.getId(),
                    usuario.getUsername(),
                    usuario.getPassword(),
                    true,
                    getPermissions()
            );
        } else {
            log.error("Usuário {} não encontrado na base de dados", username);
            throw new UsuarioNaoEncontradoException(username);
        }

    }

    private Collection<? extends GrantedAuthority> getPermissions() {

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return authorities;
    }

}
