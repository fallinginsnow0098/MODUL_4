package backend.service.impl;

import backend.model.Car;
import backend.model.Company;
import backend.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CarService implements ICarService {
    @Autowired
    private ICarService carService;
    @Override
    public Page<Car> findAll(Pageable pageable) {
        return carService.findAll(pageable);
    }

    @Override
    public Page<Car> findByName(Pageable pageable, String name) {
        return carService.findByName(pageable,name);
    }

    @Override
    public Page<Car> findByPrice(Pageable pageable, String lowPrice, String highPrice) {
        return carService.findByPrice(pageable, lowPrice, highPrice);
    }

    @Override
    public Page<Car> findByCarCompany(Pageable pageable, Company company) {
        return carService.findByCarCompany(pageable, company);
    }

    @Override
    public Car findById(long id) {
        return carService.findById(id);
    }

    @Override
    public Car save(Car car) {
        return carService.save(car);
    }

    @Override
    public void delete(long id) {
        carService.delete(id);
    }
}
