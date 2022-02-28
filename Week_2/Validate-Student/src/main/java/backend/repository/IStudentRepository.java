package backend.repository;

import backend.model.ClassRoom;
import backend.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface IStudentRepository extends PagingAndSortingRepository<Student, Long> {
    Page<Student> findAllByNameContaining(String name, Pageable pageable);
    Optional<Student> findAllByPhoneLike(String phone, Pageable pageable);
    Page<Student> findAllByPgaBetween(double minPga, double maxPga, Pageable pageable);
    Page<Student> findAllByClassRoom(ClassRoom classRoom, Pageable pageable);
}
