package backend.repository;

import backend.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ICarRepository extends PagingAndSortingRepository<Car, Long> {
    Page<Car> findAllByNameContaining(Pageable pageable, String name);
}
