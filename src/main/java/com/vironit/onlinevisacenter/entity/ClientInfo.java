package com.vironit.onlinevisacenter.entity;

import com.vironit.onlinevisacenter.entity.enums.AimOfVisit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "client_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class ClientInfo extends AbstractIdentifiableEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "sex")
    private String sex;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "passport_id")
    private Passport passport;

    @Column(name = "path_photo_on_server")
    private String photoPathOnServer;

    @Column(name = "aim_of_visit")
    @Enumerated(EnumType.STRING)
    private AimOfVisit aimOfVisit;
}
