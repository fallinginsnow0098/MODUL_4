package backend.repository;

import backend.model.ClassRoom;
import org.springframework.data.repository.CrudRepository;

public interface IClassroomRepository extends CrudRepository<ClassRoom, Long> {
}
