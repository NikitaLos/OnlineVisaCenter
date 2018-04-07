package com.vironit.onlinevisacenter.entity;

import java.time.LocalDateTime;

public class Check implements Identified<Integer>{

    private Integer id;
    private Double amount;
    private LocalDateTime dateOfPayment;
    private Application application;
    private String pathOnServer;

    @Override
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
