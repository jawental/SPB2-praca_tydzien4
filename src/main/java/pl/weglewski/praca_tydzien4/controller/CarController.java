package pl.weglewski.praca_tydzien4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.weglewski.praca_tydzien4.model.Car;
import pl.weglewski.praca_tydzien4.service.CarService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars(){
        List<Car> allCars = carService.getAllCars();
        return new ResponseEntity<>(allCars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id){
        Optional<Car> carById = carService.getCarById(id);
        return new ResponseEntity<>(carById.get(), HttpStatus.OK);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> getCarByColor(@PathVariable String color){
        List<Car> carList = carService.getCarsByColor(color);
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        boolean add = carService.addCar(car);
        if(add){
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<Car> modCar(@RequestBody Car newCar) {
        Optional<Car> first = carService.getAllCars().stream().filter(car -> car.getCarId() == newCar.getCarId()).findFirst();
        if (first.isPresent()) {
            carService.getAllCars().remove(first.get());
            carService.addCar(newCar);
            return new ResponseEntity<>(newCar, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<Car> removeCar(@PathVariable long id){
//        Optional<Car> first = carService.getAllCars().stream().filter(car -> car.getCarId() == id).findFirst();
//        if(first.isPresent()){
//            carService.removeCar(first.get());
//            return new ResponseEntity<>(first.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Car> removeCar(@PathVariable long id){
        Optional<Car> first = carService.getAllCars().stream().filter(car -> car.getCarId() == id).findFirst();
        return first.map(x -> {
                carService.removeCar(first.get());
                return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        public long maxId(){
            return carService.maxId();
        }
}