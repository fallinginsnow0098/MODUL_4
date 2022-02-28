package backend._Service;

import backend._Model.Student;

import java.util.Optional;

public interface SStudent {
    Iterable<Student> findAll();
    Optional<Student> findById(Long id);
    void save(Student student);
    void delete(Long id);

}
