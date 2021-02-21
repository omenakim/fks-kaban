package com.fks.kanban.infrastructure.security.authorization;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
class AuthUser extends User {

    private static final long serialVersionUID = 1L;
    private String username;
    private Boolean enabled;
    private Long id;

    AuthUser(Long id, String username, String password, Boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.username = username;
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return getEnabled();
    }

}
