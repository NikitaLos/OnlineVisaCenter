package com.vironit.onlinevisacenter.dto.converter;

import com.vironit.onlinevisacenter.dto.UserDTO;
import com.vironit.onlinevisacenter.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserConverter( PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User convertToEntity(UserDTO userDTO)  {
        User user = new User();
        user.setId(userDTO.getId());
        user.setRole(userDTO.getRole());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setLogin(userDTO.getLogin());
        return user;
    }

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setRole(user.getRole());
        userDTO.setEmail(user.getEmail());
        user.setPassword(userDTO.getPassword());
        userDTO.setLogin(user.getLogin());
        return userDTO;
    }
}
