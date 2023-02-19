package com.bbdproj.myjpa.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "rooms")
public class Rooms {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "roomid")
    private int roomId;
    
    @Column(name = "roomdescription")
    private String roomDescription;
    
    @Column(name = "availability")
    private Boolean availability;

    @OneToOne
    @JoinColumn(name = "roomtypeid", referencedColumnName = "roomtypeid")
    private RoomType roomTypeId;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rooms rooms = (Rooms) o;
        return roomId == rooms.roomId && Objects.equals(roomDescription, rooms.roomDescription) && Objects.equals(availability, rooms.availability) && Objects.equals(roomTypeId, rooms.roomTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, roomDescription, availability, roomTypeId);
    }
}
