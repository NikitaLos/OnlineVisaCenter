package com.vironit.onlinevisacenter.controller.user;

import com.vironit.onlinevisacenter.dto.user.UserDTO;
import com.vironit.onlinevisacenter.entity.enums.Role;
import com.vironit.onlinevisacenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/users")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/employees")
    public List<UserDTO> getEmployees() {
        return userService.findAllEmployees();
    }

    @GetMapping(value = "/roles")
    public Role[] getRoles() {
        return Role.values();
    }

    @GetMapping(value = "/employeeRoles")
    public Role[] getEmployeeRoles() {
        return new Role[]{Role.ADMIN, Role.EMPLOYEE};
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEmployee(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
    }

    @PostMapping
    public void addEmployee(@Valid @RequestBody UserDTO userDTO) {
        userService.register(userDTO);
    }
}
