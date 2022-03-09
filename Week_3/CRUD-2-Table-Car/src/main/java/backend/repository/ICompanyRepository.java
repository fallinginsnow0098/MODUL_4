package backend.repository;

import backend.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ICompanyRepository extends CrudRepository<Company, Long> {
    Iterable<Company> findAllById(long id);
}
