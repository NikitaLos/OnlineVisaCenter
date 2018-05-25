package com.vironit.onlinevisacenter.controller.user.admin;

import com.vironit.onlinevisacenter.dto.UserDTO;
import com.vironit.onlinevisacenter.dto.converter.UserConverter;
import com.vironit.onlinevisacenter.entity.User;
import com.vironit.onlinevisacenter.entity.enums.Role;
import com.vironit.onlinevisacenter.exceptions.DuplicateException;
import com.vironit.onlinevisacenter.exceptions.service.UserServiceException;
import com.vironit.onlinevisacenter.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/admin")
public class AdminUserController {

    private UserService userService;
    private UserConverter userConverter;

    @Autowired
    public AdminUserController(UserService userService, UserConverter userConverter){
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping(value = "/get_employees")
    public List<UserDTO> getEmployees() throws UserServiceException {
        List<User> employees = userService.findAllEmployees();
        return employees.stream()
                .map(user -> userConverter.convertToDTO(user))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/get_roles")
    public Role[] getRoles() {
        return Role.values();
    }

    @DeleteMapping(value = "/delete_employee/{employee_id}")
    public void deleteEmployee(@PathVariable("employee_id") Integer employeeId) throws UserServiceException {
        userService.deleteUserById(employeeId);
    }

    @PostMapping(value = "/add_employee")
    public  void addEmployee(@Valid @RequestBody UserDTO userDTO) throws UserServiceException, DuplicateException {
        User employee = userConverter.convertToEntity(userDTO);
        userService.register(employee);
    }
}
