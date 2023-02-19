package com.bbdproj.myjpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "servicecatalog")
public class ServiceCatalog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "serviceid")
    @JsonIgnore
    private int serviceId;
    
    @Column(name = "servicename")
    private String serviceName;
    
    @Column(name = "serviceamount")
    private Object serviceAmount;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Object getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(Object serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceCatalog that = (ServiceCatalog) o;
        return serviceId == that.serviceId && Objects.equals(serviceName, that.serviceName) && Objects.equals(serviceAmount, that.serviceAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, serviceName, serviceAmount);
    }
}
