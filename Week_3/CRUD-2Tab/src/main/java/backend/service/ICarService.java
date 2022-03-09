package backend.service;

import backend.model.Car;
import backend.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICarService {
    Page<Car> findAll(Pageable pageable);
    Page<Car> findByName(Pageable pageable, String name);
    Page<Car> findByPrice(Pageable pageable, String lowPrice, String highPrice);
    Page<Car> findByCarCompany(Pageable pageable, Company company);
    Car findById(long id);
    Car save(Car car);
    void delete(long id);


}
