package pl.weglewski.praca_tydzien4.service;

import org.springframework.stereotype.Service;
import pl.weglewski.praca_tydzien4.model.Car;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarServiceImp implements CarService{

    public CarServiceImp(){
        carList.add(new Car(1, "Opel", "Astra", "GRAY"));
        carList.add(new Car(2, "Ford", "Ka", "GREEN"));
        carList.add(new Car(3, "Peugeot", "307", "WHITE"));
    }

    private List<Car> carList = new ArrayList<>();

    @Override
    public List<Car> getAllCars() {
        return carList;
    }

    @Override
    public Optional<Car> getCarById(long id) {
        return getAllCars()
                .stream()
                .filter(car -> car.getCarId() == id)
                .findFirst();
    }

    @Override
    public List<Car> getCarsByColor(String color) {
        return getAllCars()
                .stream()
                .filter(car -> color.equalsIgnoreCase(car.getColor().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean addCar(Car car) {
        return carList.add(car);
    }


    @Override
    public boolean removeCar(Car car) {
        return getAllCars().remove(car);
    }

    @Override
    public long maxId(){
        Car car = Collections.max(carList, Comparator.comparing(s -> s.getCarId()));
        return car.getCarId();
    };
}
