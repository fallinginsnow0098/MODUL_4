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
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/cars")
@CrossOrigin("*")
public class CarController {
    @Autowired
    private ICarService carService;

    @GetMapping("/home")
    public ModelAndView showIndex(){
        return new ModelAndView("index");
    }
    @GetMapping
    public ResponseEntity<Page<Car>> showAll(@PageableDefault(value = 2)Pageable pageable){
        Page<Car> cars = carService.findAll(pageable);
        if (!cars.iterator().hasNext()){
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Optional<Car>> findById(@PathVariable("id") long id){
        Optional<Car> car = carService.findById(id);
        if (!car.isPresent()){
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        Car newCar = carService.save(car);
        return new ResponseEntity<>(newCar, HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Car> editCar(@RequestBody Car newCar, @PathVariable long id){
        Optional<Car> car = carService.findById(id);
        if (!car.isPresent()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        newCar.setId(car.get().getId());
        newCar = carService.save(newCar);
        return new ResponseEntity<>(newCar, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable long id){
        Optional<Car> car = carService.findById(id);
        if (!car.isPresent()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        carService.delete(car.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Car>> search(@RequestParam String searchString,@PageableDefault(value = 5) Pageable pageable){
        Page<Car> cars = carService.findByName(pageable, searchString);
        if (!cars.iterator().hasNext()){
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
}
