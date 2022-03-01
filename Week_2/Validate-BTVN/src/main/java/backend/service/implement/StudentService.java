package backend.service.implement;

import backend.model.Student;
import backend.repository.IStudentRepository;
import backend.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class StudentService implements IStudentService {
    @Autowired
    IStudentRepository studentRepository;
    @Override
    public Page<Student> finAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Page<Student> findByNameOrPhoneNumber(Pageable pageable, String search) {
        return studentRepository.findAllByNameContainingOrPhoneNumberContaining(pageable, search, search);
    }

    @Override
    public Optional<Student> findById(long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(long id) {
        studentRepository.deleteById(id);
    }
}
