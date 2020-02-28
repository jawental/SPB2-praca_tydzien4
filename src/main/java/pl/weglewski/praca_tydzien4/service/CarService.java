package pl.weglewski.praca_tydzien4.service;

import pl.weglewski.praca_tydzien4.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getAllCars();
    Optional<Car> getCarById(long id);
    List<Car> getCarsByColor(String color);
    boolean addCar(Car car);
    boolean removeCar(Car car);
    long maxId();
}
