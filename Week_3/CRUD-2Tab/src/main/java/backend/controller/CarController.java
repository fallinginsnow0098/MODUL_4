package backend.controller;

import backend.model.Car;
import backend.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/car")
public class CarController {
    @Autowired
    private ICarService carService;
    @GetMapping
    public ResponseEntity<Page<Car>> home(@PageableDefault(value = 5) Pageable pageable){
        Page<Car> cars = carService.findAll(pageable);
        if (!cars.iterator().hasNext()){
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cars,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Car> carDetail(@PathVariable("id") long id){
        Car car = carService.findById(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable("id") long id){
        carService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        Car createdCar = carService.save(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<Car> updateCar(@RequestBody Car newCar, @PathVariable long id){
        Car car = carService.findById(id);
        newCar.setId(car.getId());
        newCar = carService.save(newCar);
        return new ResponseEntity<>(newCar, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Car>> searchByName(@PageableDefault(value = 5)Pageable pageable, @RequestParam("search") String search){
        Page<Car> cars = carService.findByName(pageable, search);
        if (!cars.iterator().hasNext()){
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
    @GetMapping("/price")
    public ResponseEntity<Page<Car>> searchByPrice(@PageableDefault(value = 5)Pageable pageable, @RequestParam("lowPrice") String lowPrice, @RequestParam("hightPrice") String highPrice){
        Page<Car> cars = carService.findByPrice(pageable, lowPrice, highPrice);
        if (!cars.iterator().hasNext()){
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
}
