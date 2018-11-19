package com.vironit.onlinevisacenter.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "document_type")
@Data
@EqualsAndHashCode(callSuper = true)
public class DocumentType extends AbstractIdentifiableEntity implements Serializable {

    @Column(name = "name")
    private String name;
}
