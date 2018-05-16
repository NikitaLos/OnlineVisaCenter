package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.dao.interfaces.UserDAO;
import com.vironit.onlinevisacenter.dto.UserDTO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.dao.EntityDeleteException;
import com.vironit.onlinevisacenter.exceptions.dao.EntityFindException;
import com.vironit.onlinevisacenter.exceptions.dao.EntitySaveException;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.LoginationException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void register(User user) throws DuplicateException, UserServiceException {
        try {
            userDAO.checkDuplicate(user);
            userDAO.save(user);
        } catch (EntitySaveException e) {
            throw new UserServiceException(e);
        }
    }

    @Override
    public User logIn(User user) throws LoginationException {
        try {
            return userDAO.getUserByLoginAndPassword(user);
        } catch (EntityFindException e) {
            throw new LoginationException("User not registered");
        }
    }

    @Override
    public List<User> findAllEmployees() throws UserServiceException {
        try {
            return userDAO.findAllEmployees();
        } catch (EntityFindException e) {
            throw new UserServiceException(e);
        }
    }

    @Override
    public void deleteUserById(Integer id) throws UserServiceException {
        try {
            userDAO.deleteById(id);
        } catch (EntityDeleteException e) {
            throw new UserServiceException(e);
        }
    }

    @Override
    public User getUser(Integer id) throws UserServiceException {
        try {
            return userDAO.find(id);
        } catch (EntityFindException e) {
            throw new UserServiceException(e);
        }
    }

    @Override
    public void deleteUser(User user) throws UserServiceException {
        try {
            userDAO.delete(user);
        } catch (EntityDeleteException e) {
            throw new UserServiceException(e);
        }
    }

    @Override
    public User convertToEntity(UserDTO userDTO)  {
        User user = new User();
        user.setId(userDTO.getId());
        if(userDTO.getRole()!=null){
            user.setRole(userDTO.getRole());
        }
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setLogin(userDTO.getLogin());
        return user;
    }

    @Override
    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setRole(user.getRole());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setLogin(user.getLogin());
        return userDTO;
    }

}
