package com.bbdproj.myjpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "servicerequest")
public class ServiceRequest {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "requestid")
    @JsonIgnore
    private int requestId;

    @Column(name = "problemdesc")
    private String problemDesc;

    @Column(name = "requestdate")
    private Date requestDate;

    @Column(name = "iscomplete")
    private Boolean isComplete;

    @ManyToOne
    @JoinColumn(name = "serviceid", referencedColumnName = "serviceid")
    private ServiceCatalog serviceId;

    @OneToOne
    @JoinColumn(name = "assignedto", referencedColumnName = "staffid")
    private Staff assignedTo;

    @Column(name = "paymentid")
    @JsonIgnore
    private Integer paymentId;

    @OneToOne
    @JoinColumn(name = "roomid", referencedColumnName = "roomid")
    private Rooms roomId;

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getProblemDesc() {
        return problemDesc;
    }

    public void setProblemDesc(String problemDesc) {
        this.problemDesc = problemDesc;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Boolean isComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }


    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public ServiceCatalog getServiceId() {
        return serviceId;
    }

    public void setServiceId(ServiceCatalog serviceId) {
        this.serviceId = serviceId;
    }

    public Staff getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Staff assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Rooms getRoomId() {
        return roomId;
    }

    public void setRoomId(Rooms roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceRequest that = (ServiceRequest) o;
        return requestId == that.requestId && Objects.equals(problemDesc, that.problemDesc) && Objects.equals(requestDate, that.requestDate) && Objects.equals(isComplete, that.isComplete) && Objects.equals(serviceId, that.serviceId) && Objects.equals(assignedTo, that.assignedTo) && Objects.equals(paymentId, that.paymentId) && Objects.equals(roomId, that.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, problemDesc, requestDate, isComplete, serviceId, assignedTo, paymentId, roomId);
    }
}
