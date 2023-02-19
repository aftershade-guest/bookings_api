package com.bbdproj.myjpa.repos;

import com.bbdproj.myjpa.entities.Rooms;
import com.bbdproj.myjpa.entities.ServiceRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceRequestRepo extends CrudRepository<ServiceRequest, Integer> {

    @Modifying
    @Query("update ServiceRequest s set s.isComplete = true where s.serviceId = ?1")
    void updateisCompleteById(int id);

    @Procedure(name = "uspRequestService", procedureName = "uspRequestService")
    void uspRequestService(int roomId, int serviceId, String problemdesc);

    List<ServiceRequest> findByroomId(Rooms room);

}
