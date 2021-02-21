package com.fks.kanban.infrastructure.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SecurityService {

    public Long userId() {
        return getJwt().isPresent() ? ((Long) getJwt().get().getClaim("id")) : 0L;
    }

    public Boolean isAuthenticated() {
        return getAuthentication().isAuthenticated();
    }

    public Boolean authenticatedUserEquals(Long anUserId) {
        Long userId = userId();
        return userId != null && userId.equals(anUserId);
    }

    public Boolean hasAuthority(String aName) {
        return getRoles().stream().anyMatch(authority -> authority.equalsIgnoreCase(aName));
    }

    public Boolean hasWriteScope() {
        return getScopes().stream().anyMatch(authority -> authority.equalsIgnoreCase("write"));
    }

    public Boolean hasReadScope() {
        return getScopes().stream().anyMatch(authority -> authority.equalsIgnoreCase("read"));
    }

    public String getUsername() {
        Optional<Jwt> maybeJwt = getJwt();
        return maybeJwt.isPresent() ? maybeJwt.get().getClaim("user_name") : "anonymousUser";
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private Optional<Jwt> getJwt() {
        if (getAuthentication() != null && getAuthentication().getPrincipal() != "anonymousUser") {
            return Optional.of((Jwt) getAuthentication().getPrincipal());
        }
        return Optional.empty();
    }

    private List<String> getRoles() {
        Optional<Jwt> maybeJwt = getJwt();
        return maybeJwt.isPresent() ? maybeJwt.get().getClaim("authorities") : new ArrayList<>();
    }

    private List<String> getScopes() {
        Optional<Jwt> maybeJwt = getJwt();
        return maybeJwt.isPresent() ? maybeJwt.get().getClaim("scope") : new ArrayList<>();
    }

}
