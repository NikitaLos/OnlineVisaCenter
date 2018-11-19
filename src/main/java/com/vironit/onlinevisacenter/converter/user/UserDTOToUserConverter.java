package com.vironit.onlinevisacenter.converter.user;

import com.vironit.onlinevisacenter.dto.user.UserDTO;
import com.vironit.onlinevisacenter.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDTOToUserConverter implements Converter<UserDTO, User> {

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public User convert(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return user;
    }
}
