package com.vironit.onlinevisacenter.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "visa", schema = "visa_center")
public class Visa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany
    @JoinTable(name = "visa_document_type", schema = "visa_center",
                joinColumns = {@JoinColumn(name = "visa_id")},
                inverseJoinColumns = {@JoinColumn(name = "document_type_id")})
    private List<DocumentType> requiredDocumentTypes;

    public Visa() {
        this.requiredDocumentTypes = new ArrayList<>();
    }



    public void addDocumentType(DocumentType documentType){
        requiredDocumentTypes.add(documentType);
    }

    public void removeDocumentType(DocumentType documentType){
        requiredDocumentTypes.remove(documentType);
    }
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

    public List<DocumentType> getRequiredDocumentTypes() {
        return requiredDocumentTypes;
    }

    public void setRequiredDocumentTypes(List<DocumentType> requiredDocumentTypes) {
        this.requiredDocumentTypes = requiredDocumentTypes;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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
