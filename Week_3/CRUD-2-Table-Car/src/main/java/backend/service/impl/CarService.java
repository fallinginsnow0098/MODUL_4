package backend.service.impl;

import backend.model.Car;
import backend.repository.ICarRepository;
import backend.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService implements ICarService {
    @Autowired
    private ICarRepository carRepository;
    @Override
    public Page<Car> findAll(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    @Override
    public Optional<Car> findById(long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car save(Car car) {
        carRepository.save(car);
        return car;
    }

    @Override
    public void delete(Car car) {
        carRepository.delete(car);
    }

    @Override
    public Page<Car> findByName(Pageable pageable, String name) {
        return carRepository.findAllByNameContaining(pageable,name);
    }
}
