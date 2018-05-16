package com.vironit.onlinevisacenter.dto;

import com.vironit.onlinevisacenter.entity.enums.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserDTO {

    private Integer id;

    @NotNull(message = "login can not be null")
    @Size(min = 5,max = 20, message = "login must have from {min} to {max} characters")
    private String login;

    @NotNull(message = "password can not be null")
    @Size(min = 5,max = 20, message = "password must have from {min} to {max} characters")
    private String password;

    @Email
    @NotNull(message = "email can not be null")
    private String email;

    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
