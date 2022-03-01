package backend.repository;

import backend.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IStudentRepository extends PagingAndSortingRepository<Student, Long> {
    Page<Student> findAllByNameContainingOrPhoneNumberContaining(Pageable pageable, String search, String search1);
}
