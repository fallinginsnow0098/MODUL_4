package backend.service.implement;

import backend.model.ClassRoom;
import backend.model.Student;
import backend.repository.IStudentRepository;
import backend.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private IStudentRepository studentRepository;
    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Optional<Student> findById(long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Page<Student> findAllByName(String name, Pageable pageable) {
        return studentRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Optional<Student> findAllByPhone(String phone, Pageable pageable) {
        return studentRepository.findAllByPhoneLike(phone, pageable);
    }

    @Override
    public Page<Student> findAllByPga(double minPga, double maxPga, Pageable pageable) {
        return studentRepository.findAllByPgaBetween(minPga, maxPga, pageable);
    }

    @Override
    public Page<Student> findAllByClassroom(ClassRoom classRoom, Pageable pageable) {
        return studentRepository.findAllByClassRoom(classRoom, pageable);
    }
}
