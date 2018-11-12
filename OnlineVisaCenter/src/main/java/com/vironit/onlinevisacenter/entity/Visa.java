package com.vironit.onlinevisacenter.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "visa")
@Data
@EqualsAndHashCode(callSuper = true)
public class Visa extends AbstractIdentifiableEntity implements Serializable {

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private Double price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "visa_document_type",
                joinColumns = {@JoinColumn(name = "visa_id")},
                inverseJoinColumns = {@JoinColumn(name = "document_type_id")})
    private List<DocumentType> requiredDocumentTypes;

    public Visa() {
        this.requiredDocumentTypes = new ArrayList<>(); //todo
    }
}
