package com.bbdproj.myjpa.repos;

import com.bbdproj.myjpa.entities.Bookings;
import com.bbdproj.myjpa.entities.Customers;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


public interface BookingsRepository extends CrudRepository<Bookings, Integer> {

    Iterable<Bookings> findBycustomerId(Customers customer);

    @Procedure(procedureName = "uspMakeBooking", value = "uspMakeBooking")
    int uspMakeBooking(int customerId, Date checkInDate, Date checkOutDate, int RoomId);

    @Procedure(procedureName = "uspCheckOut", value = "uspCheckOut")
    void uspCheckOut(int bookingId);

    @Procedure(procedureName = "uspChangeStatus", value = "uspChangeStatus")
    void uspChangeStatus(int bookingId);

    @Transactional
    @Modifying
    @Query("update Bookings b set b.isCheckedIn = true where b.bookingId = ?1")
    void checkUserInByBooking(int bookingId);


}
