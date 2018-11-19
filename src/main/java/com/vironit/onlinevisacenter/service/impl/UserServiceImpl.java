package com.vironit.onlinevisacenter.service.impl;

import com.vironit.onlinevisacenter.converter.user.UserDTOToUserConverter;
import com.vironit.onlinevisacenter.converter.user.UserToUserDTOConverter;
import com.vironit.onlinevisacenter.dto.user.UserDTO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.EntityNotFoundException;
import com.vironit.onlinevisacenter.repository.jpa.UserDAO;
import com.vironit.onlinevisacenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserToUserDTOConverter toUserDTOConverter;

    @Autowired
    private UserDTOToUserConverter toUserConverter;

    @Override
    public void register(UserDTO userDTO) {
        User user = toUserConverter.convert(userDTO);
        checkDuplicate(user);
        userDAO.save(user);
    }

    @Override
    public UserDTO logIn(UserDTO userDTO) {
        return toUserDTOConverter.convert(userDAO.findByLoginAndPassword(userDTO.getLogin(), userDTO.getPassword()));
    }

    @Override
    public List<UserDTO> findAllEmployees() {
        return userDAO.findAllEmployees().stream()
                .map((user -> toUserDTOConverter.convert(user)))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Integer id) {
        userDAO.deleteById(id);
    }

    @Override
    public UserDTO getUser(Integer id) {
        return toUserDTOConverter.convert(userDAO.findById(id).orElseThrow(() -> new EntityNotFoundException(id)));
    }

    @Override
    public UserDTO getUserByLogin(String login) {
        return toUserDTOConverter.convert(userDAO.findByLogin(login));
    }

    private void checkDuplicate(User user) {
        if (!userDAO.findByLoginOrEmail(user.getLogin(), user.getEmail()).isEmpty()) {
            throw new DuplicateException("Such a user already exists");
        }
    }
}
