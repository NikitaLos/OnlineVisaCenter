package com.vironit.onlinevisacenter.config;

import com.vironit.onlinevisacenter.dao.interfaces.UserDAO;
import com.vironit.onlinevisacenter.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@SpringBootConfiguration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDAO userDAO;
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public SecurityConfig(UserDAO userDAO, AuthenticationSuccessHandler authenticationSuccessHandler){
        this.userDAO = userDAO;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(new UserDetailServiceImpl(userDAO));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/employee/**").hasAuthority("EMPLOYEE")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/client/**").hasAuthority("CLIENT")
                .antMatchers("/auth_user/**").hasAnyAuthority("CLIENT","EMPLOYEE")
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/login_user")
                .usernameParameter("login").passwordParameter("password")
                .successHandler(authenticationSuccessHandler)
                .and()
                .logout().logoutUrl("/logout")
                .and()
                .csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
