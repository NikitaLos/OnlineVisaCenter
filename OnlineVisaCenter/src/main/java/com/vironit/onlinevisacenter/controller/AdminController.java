package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.dto.Message;
import com.vironit.onlinevisacenter.dto.UserDTO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/view_employees",method = RequestMethod.GET)
    public List<UserDTO> showEmployees() throws UserServiceException {
        List<User> employees = userService.findAllEmployees();
        return employees.stream()
                .map(user -> userService.convertToDTO(user))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/delete_user/{employee_id}",method = RequestMethod.GET)
    public  Message deleteEmployee(@PathVariable("employee_id") Integer employeeId) throws UserServiceException {
        userService.deleteUserById(employeeId);
        return new Message("success");
    }

    @RequestMapping(value = "/add_employee",method = RequestMethod.POST)
    public  Message addEmployee(@RequestBody UserDTO userDTO) throws UserServiceException, DuplicateException {
        User employee = userService.convertToEntity(userDTO);
        userService.register(employee);
        return new Message("success");
    }

    @ExceptionHandler(DuplicateException.class)
    public Message duplicateUser(DuplicateException e) {
        return new Message(e.getMessage());
    }
}
