package com.vironit.onlinevisacenter.service;

import com.vironit.onlinevisacenter.repository.jpa.UserDAO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.ServiceException;
import com.vironit.onlinevisacenter.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void register(User user) throws ServiceException {
        checkDuplicate(user);
        userDAO.save(user);
    }

    @Override
    public User logIn(User user) {
        return userDAO.findByLoginAndPassword(user.getLogin(),user.getPassword());
    }

    @Override
    public List<User> findAllEmployees() {
        return userDAO.findAllEmployees();
    }

    @Override
    public void deleteUserById(Integer id) {
        userDAO.deleteById(id);
    }

    @Override
    public User getUser(Integer id) throws ServiceException {
        return userDAO.findById(id).orElseThrow(ServiceException::new);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDAO.findByLogin(login);
    }

    private void checkDuplicate(User user) throws ServiceException {
        if (!userDAO.findByLoginOrEmail(user.getLogin(), user.getEmail()).isEmpty()) {
            throw new ServiceException("Such a user already exists");
        }
    }
}
