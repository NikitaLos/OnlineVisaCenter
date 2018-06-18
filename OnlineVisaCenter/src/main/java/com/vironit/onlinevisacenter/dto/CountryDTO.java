package com.vironit.onlinevisacenter.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountryDTO {

    private Integer id;

    @NotNull(message = "{country.name.null}")
    @Size(min = 3, message = "{country.name.size}")
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
