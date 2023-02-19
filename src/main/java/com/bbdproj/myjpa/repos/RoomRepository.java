package com.bbdproj.myjpa.repos;

import com.bbdproj.myjpa.entities.Rooms;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Rooms, Integer> {
}
