package com.vironit.onlinevisacenter.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "check",schema = "visa_center")
public class Check implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date_of_payment")
    private LocalDateTime dateOfPayment;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "check")
    private Application application;

    @Column(name = "path_on_server")
    private String pathOnServer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(LocalDateTime dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public String getPathOnServer() {
        return pathOnServer;
    }

    public void setPathOnServer(String pathOnServer) {
        this.pathOnServer = pathOnServer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Check check = (Check) o;

        if (amount != null ? !amount.equals(check.amount) : check.amount != null) return false;
        if (dateOfPayment != null ? !dateOfPayment.equals(check.dateOfPayment) : check.dateOfPayment != null)
            return false;
        if (application != null ? !application.equals(check.application) : check.application != null)
            return false;
        return pathOnServer != null ? pathOnServer.equals(check.pathOnServer) : check.pathOnServer == null;
    }

    @Override
    public int hashCode() {
        int result = amount != null ? amount.hashCode() : 0;
        result = 31 * result + (dateOfPayment != null ? dateOfPayment.hashCode() : 0);
        result = 31 * result + (application != null ? application.hashCode() : 0);
        result = 31 * result + (pathOnServer != null ? pathOnServer.hashCode() : 0);
        return result;
    }
}
