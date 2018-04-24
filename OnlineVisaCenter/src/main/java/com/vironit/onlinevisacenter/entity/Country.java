package com.vironit.onlinevisacenter.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "country", schema = "visa_center")
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "country")
    private List<Visa> availableVisas;

    public Country() {
        availableVisas = new ArrayList<>();
    }

    public void addVisa(Visa visa) {
        availableVisas.add(visa);
    }

    public void removeVisa(Visa visa) {
        availableVisas.remove(visa);
    }

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
