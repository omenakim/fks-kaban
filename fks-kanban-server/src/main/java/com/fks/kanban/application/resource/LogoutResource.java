package com.fks.kanban.application.resource;


import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth/token")
class LogoutResource {

    @DeleteMapping("/revoke")
    @PreAuthorize("@securityService.isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void revokeToken(HttpServletRequest req, HttpServletResponse resp) {

        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(Boolean.TRUE);
        cookie.setSecure( Boolean.FALSE);
        cookie.setPath(req.getContextPath() + "/oauth/token");
        cookie.setMaxAge(0);

        resp.addCookie(cookie);
    }

}
