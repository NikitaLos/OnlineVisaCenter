package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.dto.Message;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/manage_employees",method = RequestMethod.GET)
    public List<User> showEmployees() throws UserServiceException {
        return userService.findAllEmployees();
    }

    @RequestMapping(value = "/delete_user/{employee_id}",method = RequestMethod.GET)
    public  Message deleteEmployee(@PathVariable("employee_id") Integer employeeId) throws UserServiceException {
        userService.deleteUserById(employeeId);
        return new Message("success");
    }

    @RequestMapping(value = "/add_employee",method = RequestMethod.POST)
    public  Message addEmployee(@RequestBody User employee) throws UserServiceException, DuplicateException {
        userService.register(employee);
        return new Message("success");
    }


    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.MULTIPLE_CHOICES)
    public Message duplicateUser(DuplicateException e) {
        return new Message(e.getMessage());
    }
}
