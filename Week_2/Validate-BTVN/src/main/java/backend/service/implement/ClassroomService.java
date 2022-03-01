package backend.service.implement;

import backend.model.ClassRoom;
import backend.repository.IClassroomRepository;
import backend.service.IClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ClassroomService implements IClassroomService {
    @Autowired
    IClassroomRepository classroomRepository;
    @Override
    public Iterable<ClassRoom> findAllClass() {
        return classroomRepository.findAll();
    }

    @Override
    public Optional<ClassRoom> findClassRoomById(long id) {
        return classroomRepository.findById(id);
    }
}
