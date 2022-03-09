package backend.service;

import backend.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICarService {
    Page<Car> findAll(Pageable pageable);
    Optional<Car> findById(long id);
    Car save(Car car);
    void delete(Car car);
    Page<Car> findByName(Pageable pageable, String name);
}
