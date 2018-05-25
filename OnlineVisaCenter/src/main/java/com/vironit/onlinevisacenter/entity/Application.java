package com.vironit.onlinevisacenter.entity;

import com.vironit.onlinevisacenter.entity.enums.Result;
import com.vironit.onlinevisacenter.entity.enums.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "application")
public class Application implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_info_id")
    private ClientInfo clientInfo;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "visa_info_id")
    private VisaInfo visaInfo;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "result")
    @Enumerated(EnumType.STRING)
    private Result result;

    @Column(name = "comments")
    private String comments;

    @Column(name = "date_of_create")
    private LocalDateTime creationTime;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (clientInfo != null ? !clientInfo.equals(that.clientInfo) : that.clientInfo != null) return false;
        if (visaInfo != null ? !visaInfo.equals(that.visaInfo) : that.visaInfo != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (status != that.status) return false;
        if (result != that.result) return false;
        if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;
        return creationTime != null ? creationTime.equals(that.creationTime) : that.creationTime == null;
    }

    @Override
    public int hashCode() {
        int result1 = id != null ? id.hashCode() : 0;
        result1 = 31 * result1 + (clientInfo != null ? clientInfo.hashCode() : 0);
        result1 = 31 * result1 + (visaInfo != null ? visaInfo.hashCode() : 0);
        result1 = 31 * result1 + (user != null ? user.hashCode() : 0);
        result1 = 31 * result1 + (status != null ? status.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (comments != null ? comments.hashCode() : 0);
        result1 = 31 * result1 + (creationTime != null ? creationTime.hashCode() : 0);
        return result1;
    }
}
