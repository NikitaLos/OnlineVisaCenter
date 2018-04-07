package com.vironit.onlinevisacenter.entity;

import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.entity.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "application")
public class Application implements Identified<Integer> {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private ClientInfo clientInfo;

//    @Column(name = "client_info_id")
//    private VisaInfo visaInfo;

    private User client;

    @Column(name = "status")
    private Status status;

    @Column(name = "result")
    private Result result;

    @Column(name = "comments")
    private String comments;

    @Column(name = "check_id")
    private Check check;

    @Column(name = "date_of_create")
    private LocalDateTime creationTime;


    public Application() {
        this.creationTime = LocalDateTime.now();
        this.status = Status.IN_VC_QUEUE;
        this.result = Result.NO_RESULT;
    }


    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    public VisaInfo getVisaInfo() {
        return visaInfo;
    }

    public void setVisaInfo(VisaInfo visaInfo) {
        this.visaInfo = visaInfo;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Application that = (Application) o;

        if (clientInfo != null ? !clientInfo.equals(that.clientInfo) : that.clientInfo != null) return false;
        if (visaInfo != null ? !visaInfo.equals(that.visaInfo) : that.visaInfo != null) return false;
        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        if (status != that.status) return false;
        if (result != that.result) return false;
        if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;
        if (check != null ? !check.equals(that.check) : that.check != null) return false;
        return creationTime != null ? creationTime.equals(that.creationTime) : that.creationTime == null;
    }

    @Override
    public int hashCode() {
        int result1 = clientInfo != null ? clientInfo.hashCode() : 0;
        result1 = 31 * result1 + (visaInfo != null ? visaInfo.hashCode() : 0);
        result1 = 31 * result1 + (client != null ? client.hashCode() : 0);
        result1 = 31 * result1 + (status != null ? status.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (comments != null ? comments.hashCode() : 0);
        result1 = 31 * result1 + (check != null ? check.hashCode() : 0);
        result1 = 31 * result1 + (creationTime != null ? creationTime.hashCode() : 0);
        return result1;
    }
}
