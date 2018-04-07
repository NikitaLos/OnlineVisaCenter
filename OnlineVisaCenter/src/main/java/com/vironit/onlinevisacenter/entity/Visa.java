package com.vironit.onlinevisacenter.entity;

import java.util.List;

public class Visa implements Identified<Integer> {

    private Integer id;
    private String type;
    private Double price;
    private List<Document> requiredDocuments;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Document> getRequiredDocuments() {
        return requiredDocuments;
    }

    public void setRequiredDocuments(List<Document> requiredDocuments) {
        this.requiredDocuments = requiredDocuments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Visa visa = (Visa) o;

        return type != null ? type.equals(visa.type) : visa.type == null;
    }

    @Override
    public int hashCode() {
        return type != null ? type.hashCode() : 0;
    }
}
