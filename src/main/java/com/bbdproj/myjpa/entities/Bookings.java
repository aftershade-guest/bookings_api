package com.bbdproj.myjpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "bookings")
public class Bookings {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "bookingid")
    @JsonIgnore
    private int bookingId;

    @Column(name = "bookdate")
    private Date bookDate;

    @Column(name = "chargeamount")
    private Object chargeAmount;

    @Column(name = "checkindate")
    private Date checkInDate;

    @Column(name = "checkoutdate")
    private Date checkOutDate;

    @Column(name = "ischeckedout")
    private Boolean isCheckedOut;

    @Column(name = "ischeckedin")
    private Boolean isCheckedIn;

    @OneToOne
    @JoinColumn(name = "roomid", referencedColumnName = "roomid")
    private Rooms roomId;

    @ManyToOne
    @JoinColumn(name = "customerid", referencedColumnName = "customerid")
    private Customers customerId;

    @Column(name = "paymentid")
    @JsonIgnore
    private Integer paymentId;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public Object getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(Object chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Boolean getCheckedOut() {
        return isCheckedOut;
    }

    public void isCheckedOut(Boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public Boolean isCheckedIn() {
        return isCheckedIn;
    }

    public void setCheckedIn(Boolean checkedIn) {
        isCheckedIn = checkedIn;
    }

    public Rooms getRoomId() {
        return roomId;
    }

    public void setRoomId(Rooms roomId) {
        this.roomId = roomId;
    }

    public Customers getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customers customerId) {
        this.customerId = customerId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookings bookings = (Bookings) o;
        return bookingId == bookings.bookingId && Objects.equals(bookDate, bookings.bookDate) && Objects.equals(chargeAmount, bookings.chargeAmount) && Objects.equals(checkInDate, bookings.checkInDate) && Objects.equals(checkOutDate, bookings.checkOutDate) && Objects.equals(isCheckedOut, bookings.isCheckedOut) && Objects.equals(isCheckedIn, bookings.isCheckedIn) && Objects.equals(roomId, bookings.roomId) && Objects.equals(customerId, bookings.customerId) && Objects.equals(paymentId, bookings.paymentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, bookDate, chargeAmount, checkInDate, checkOutDate, isCheckedOut, isCheckedIn, roomId, customerId, paymentId);
    }
}
