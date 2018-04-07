package com.vironit.onlinevisacenter.entity;

import java.util.List;

public class Country implements Identified<Integer> {

    private Integer id;
    private String name;
    private List<Visa> availableVisas;

    @Override
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

    public List<Visa> getAvailableVisas() {
        return availableVisas;
    }

    public void setAvailableVisas(List<Visa> availableVisas) {
        this.availableVisas = availableVisas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        return name != null ? name.equals(country.name) : country.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
