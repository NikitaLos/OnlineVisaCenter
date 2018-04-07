package com.vironit.onlinevisacenter.entity.enums;

public enum  Role {
    EMPLOYEE("employee"),ADMIN("admin"),CLIENT("client");

    private String roleString;

    Role(String roleString) {
        this.roleString = roleString;
    }
    public String getRoleString() {
        return roleString;
    }
}
