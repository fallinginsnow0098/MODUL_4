package backend._Repository;

import backend._Model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IStudentRepository extends CrudRepository<Student, Long>, PagingAndSortingRepository<Student, Long> {
}
