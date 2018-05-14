package com.vironit.onlinevisacenter.controller;

import com.vironit.onlinevisacenter.dto.Message;
import com.vironit.onlinevisacenter.dto.UserDTO;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.entity.enums.Role;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.service.inrefaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/get_employees")
    public List<UserDTO> getEmployees() throws UserServiceException {
        List<User> employees = userService.findAllEmployees();
        return employees.stream()
                .map(user -> userService.convertToDTO(user))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/get_roles")
    public Role[] getRoles()  {
        return Role.values();
    }

    @DeleteMapping(value = "/delete_employee/{employee_id}")
    public void deleteEmployee(@PathVariable("employee_id") Integer employeeId) throws UserServiceException {
        userService.deleteUserById(employeeId);
    }

    @PostMapping(value = "/add_employee")
    public  void addEmployee(@RequestBody UserDTO userDTO) throws UserServiceException, DuplicateException {
        User employee = userService.convertToEntity(userDTO);
        userService.register(employee);
    }
}
