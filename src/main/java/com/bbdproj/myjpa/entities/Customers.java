package com.bbdproj.myjpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

import static com.bbdproj.myjpa.ValidationsUtil.validateName;

@Entity
@Table(name = "customers")
public class Customers {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customerid")
    @JsonIgnore
    private int customerId;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "firstname")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Column(name = "lastname")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Column(name = "email")
    private String email;

    public Customers(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Customers() {

    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {

        if (validateName(firstName)) {
            this.firstName = firstName;
        }

        throw new IllegalArgumentException("Incorrect first name format.");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {

        if (validateName(lastName)) {
            this.lastName = lastName;
        }

        throw new IllegalArgumentException("Incorrect last name format.");

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        if (validateName(email)) {
            this.email = email;
        }

        throw new IllegalArgumentException("Incorrect email format.");

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customers customers = (Customers) o;
        return customerId == customers.customerId && Objects.equals(firstName, customers.firstName) && Objects.equals(lastName, customers.lastName) && Objects.equals(email, customers.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, email);
    }
}
