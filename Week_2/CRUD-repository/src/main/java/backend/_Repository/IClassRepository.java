package backend._Repository;

import backend._Model.Classes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IClassRepository extends CrudRepository<Classes, Long> {
}
