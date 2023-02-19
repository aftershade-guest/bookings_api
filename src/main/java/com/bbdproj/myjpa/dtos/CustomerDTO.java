package com.bbdproj.myjpa.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDTO {

    private int customerId;
    private Date checkInDate;

    private Date checkOutDate;

    private int RoomId;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public int getRoomId() {
        return RoomId;
    }

    public void setRoomId(int roomId) {
        RoomId = roomId;
    }

    public boolean checkDate() {
        return checkInDate.before(checkOutDate);
    }

}
