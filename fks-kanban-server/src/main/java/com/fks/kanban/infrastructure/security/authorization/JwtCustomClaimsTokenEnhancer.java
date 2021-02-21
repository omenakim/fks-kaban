package com.fks.kanban.infrastructure.security.authorization;


import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

class JwtCustomClaimsTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        if (!authentication.isClientOnly()) {
            AuthUser authUser = (AuthUser) authentication.getPrincipal();

            Map<String, Object> addInfo = new HashMap<>();
            addInfo.put("id", authUser.getId());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
        }
        return accessToken;
    }

}
