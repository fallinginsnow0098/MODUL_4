package backend.service.implement;

import backend.model.ClassRoom;
import backend.repository.IClassRoomRepository;
import backend.service.IClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassRoomService implements IClassRoomService {
    @Autowired
    private IClassRoomRepository classRoomRepository;
    @Override
    public Iterable<ClassRoom> findAllClassrooms() {
        return classRoomRepository.findAll();
    }

    @Override
    public Optional<ClassRoom> findById(long id) {
        return classRoomRepository.findById(id);
    }
}
