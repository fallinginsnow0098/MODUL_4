package backend.service;

import backend.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IStudentService {
    Page<Student> finAll(Pageable pageable);
    Page<Student> findByNameOrPhoneNumber(Pageable pageable, String search);
   Optional<Student> findById(long id);
   Student save(Student student);
   void delete(long id);
}
