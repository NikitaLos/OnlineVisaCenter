package com.vironit.onlinevisacenter.controller.user;

import com.vironit.onlinevisacenter.dto.user.UserDTO;
import com.vironit.onlinevisacenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RegistrationUserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/login_success")
    public Map<String, String> loginSuccessRedirect(@RequestParam("dest") String destination) {
        Map<String, String> map = new HashMap<>();
        map.put("destination", destination);
        return map;
    }

    @PostMapping(value = "/register")
    public void processRegister(@Valid @RequestBody UserDTO userDTO) {
        userService.register(userDTO);
    }
}
