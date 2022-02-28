package backend.service;

import backend.model.ClassRoom;

import java.util.Optional;

public interface IClassRoomService {
    Iterable<ClassRoom> findAllClassrooms();
    Optional<ClassRoom> findById(long id);
}
