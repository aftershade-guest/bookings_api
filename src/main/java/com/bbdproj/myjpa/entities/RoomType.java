package com.bbdproj.myjpa.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "roomtype")
public class RoomType {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "roomtypeid")
    private String roomTypeId;

    @Column(name = "typedescription")
    private String typeDescription;

    @Column(name = "roomprice")
    private Object roomPrice;

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public Object getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Object roomPrice) {
        this.roomPrice = roomPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomType roomType = (RoomType) o;
        return Objects.equals(roomTypeId, roomType.roomTypeId) && Objects.equals(typeDescription, roomType.typeDescription) && Objects.equals(roomPrice, roomType.roomPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomTypeId, typeDescription, roomPrice);
    }
}
