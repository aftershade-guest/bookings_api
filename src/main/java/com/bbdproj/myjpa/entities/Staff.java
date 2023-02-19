package com.bbdproj.myjpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

import static com.bbdproj.myjpa.ValidationsUtil.validateName;

@Entity
@Table(name = "staff")
public class Staff {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "staffid")
    @JsonIgnore
    private int staffId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @OneToOne
    @JoinColumn(name = "roleid", referencedColumnName = "roleid")
    private StaffRoles roleId;

    public Staff() {
    }

    public Staff(String firstName, String lastName, StaffRoles roleId) {
        setFirstName(firstName);
        setLastName(lastName);
        this.roleId = roleId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {

        if (validateName(firstName)) {
            this.firstName = firstName;
        }

        throw new IllegalArgumentException("First name has incorrect format");

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {

        if (validateName(lastName)) {
            this.lastName = lastName;
        }

        throw new IllegalArgumentException("First name has incorrect format");
    }

    public StaffRoles getRoleId() {
        return roleId;
    }

    public void setRoleId(StaffRoles roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return staffId == staff.staffId && Objects.equals(firstName, staff.firstName) && Objects.equals(lastName, staff.lastName) && Objects.equals(roleId, staff.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffId, firstName, lastName, roleId);
    }
}
