package com.vironit.onlinevisacenter.config.security;

import com.vironit.onlinevisacenter.exceptions.UndefinedRoleException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class RoleBasedAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                authorities.forEach(authority -> {
                    try {
                        if (authority.getAuthority().equals("CLIENT")) {
                            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/client");
                        } else if (authority.getAuthority().equals("ADMIN")) {
                            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/admin");
                        } else if (authority.getAuthority().equals("EMPLOYEE")) {
                            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/employee");
                        } else {
                            throw new IllegalStateException();
                        }
                        } catch (Exception e){
                        }
                });

    }
}
