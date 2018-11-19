package com.vironit.onlinevisacenter.dto.user;

import com.vironit.onlinevisacenter.entity.enums.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDTO {

    private Integer id;

    @NotNull(message = "{user.login.null}")
    @Size(min = 5,max = 20, message = "{user.login.size}")
    private String login;

    @NotNull(message = "{user.password.null}")
    @Size(min = 5,max = 20, message = "{user.password.size}")
    private String password;

    @Email
    @NotNull(message = "{user.email.null}")
    private String email;

    @NotNull(message = "{user.role.null}")
    private Role role;
}
