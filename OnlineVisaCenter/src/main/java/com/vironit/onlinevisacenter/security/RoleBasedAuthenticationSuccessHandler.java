package com.vironit.onlinevisacenter.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class RoleBasedAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                authorities.forEach(authority -> {
                    try {
                        if (authority.getAuthority().equals("CLIENT")) {
                            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/login_success?dest=client_page.html");
                        } else if (authority.getAuthority().equals("ADMIN")) {
                            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/login_success?dest=admin_page.html");
                        } else if (authority.getAuthority().equals("EMPLOYEE")) {
                            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/login_success?dest=employee_page.html");
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

    }
}
