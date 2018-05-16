package com.vironit.onlinevisacenter.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DocumentTypeDTO {

    private Integer id;

    @NotNull
    @Size(min = 2, message = "name must have {min} characters minimum")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
