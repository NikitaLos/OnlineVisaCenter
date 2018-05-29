package com.vironit.onlinevisacenter.controller.user.authorized;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthorizedUserController {

    @GetMapping(value = "/login_success")
    public Map<String, String> loginSuccessRedirect(@RequestParam("dest") String destination) {
        Map<String,String> map = new HashMap<>();
        map.put("destination",destination);
        return map;
    }

    @GetMapping(value = "/logout")
    public void logOut(HttpSession session)  {
        session.invalidate();
    }
}
