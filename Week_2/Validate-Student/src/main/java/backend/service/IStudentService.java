package backend.service;

import backend.model.ClassRoom;
import backend.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface IStudentService {
    Page<Student> findAll(Pageable pageable);
    Student save(Student student);
    void delete(long id);
    Optional<Student> findById(long id);
    Page<Student> findAllByName(String name, Pageable pageable);
    Optional<Student> findAllByPhone(String phone, Pageable pageable);
    Page<Student> findAllByPga(double minPga, double maxPga, Pageable pageable);
    Page<Student> findAllByClassroom(ClassRoom classRoom, Pageable pageable);
}
