package backend.repository;

import backend.model.Car;
import backend.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarRepository extends PagingAndSortingRepository<Car, Long> {
    Page<Car> findAllByNameContaining(Pageable pageable, String name);
    Page<Car> findAllByPriceAndPrice(Pageable pageable, double price, double price2);
    Page<Car> findAllByCarCompany(Pageable pageable, Company carCompany);
}
