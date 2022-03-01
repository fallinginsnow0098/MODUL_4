package backend.service;

import backend.model.ClassRoom;

import java.util.Optional;

public interface IClassroomService {
    Iterable<ClassRoom> findAllClass();
    Optional<ClassRoom> findClassRoomById(long id);
}
