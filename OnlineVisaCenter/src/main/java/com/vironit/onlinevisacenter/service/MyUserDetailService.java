package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.UserDAO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class MyUserDetailService implements UserDetailsService {

    private UserDAO userDAO;

    @Autowired
    public MyUserDetailService(UserDAO userDAO) {
        this.userDAO=userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user;
        try {
            user = userDAO.findUserByLogin(login);
        } catch (EntityFindException e) {
            throw new UsernameNotFoundException("User + " + login + " not found");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(user.getLogin(),user.getPassword(),authorities);

    }
}
