package com.fks.kanban.infrastructure.security.authorization;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getMethod().getName().equals("postAccessToken");
    }

    @Override
    public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter returnType,
                                      MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                      ServerHttpRequest request, ServerHttpResponse response) {

        var req =  ((ServletServerHttpRequest) request).getServletRequest();
        var resp = ((ServletServerHttpResponse)response).getServletResponse();

        if (body.getRefreshToken() != null) {
            DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;
            String refreshToken = body.getRefreshToken().getValue();
            addRefreshTokenInCookie(refreshToken, req, resp);
            removeRefreshTokenFromBody(token);
        }

        return body;
    }

    private void removeRefreshTokenFromBody(DefaultOAuth2AccessToken token) {
        token.setRefreshToken(null);
    }

    private void addRefreshTokenInCookie(String refreshToken, HttpServletRequest req, HttpServletResponse resp) {
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(Boolean.TRUE);
        refreshTokenCookie.setSecure(Boolean.FALSE);
        refreshTokenCookie.setPath(req.getContextPath() + "/oauth/token");
        refreshTokenCookie.setMaxAge(36000);
        resp.addCookie(refreshTokenCookie);
    }

}