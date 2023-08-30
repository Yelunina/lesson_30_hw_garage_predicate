package ait.cars.dao;

import ait.cars.model.Car;

import java.util.function.Predicate;

public class GarageImpl implements Garage {
    private Car[] cars;
    private int size;

    public GarageImpl(int capacity) {
        cars = new Car[capacity];
    }

    @Override
    public boolean addCar(Car car) {
        if (car == null || size == cars.length || findCarByRegNumber(car.getRegNumber()) != null) {
            return false;
        }
        cars[size++] = car;
        return true;
    }

    @Override
    public Car removeCar(String regNumber) {
        for (int i = 0; i < size; i++) {
            if (regNumber.equals(cars[i].getRegNumber())) {
                Car temp = cars[i];
                cars[i] = cars[--size];
                return temp;
            }

        }
        return null;
    }

    @Override
    public Car[] findCarByRegNumber(String regNumber) {
        Predicate<Car> predicate = с -> с.getRegNumber() = regNumber;
        return findCarsByPredicate(predicate);
    }

    @Override
    public Car[] findCarsByModel(String model) {
        Predicate<Car> predicate = с -> с.getModel() = model;
        return findCarsByPredicate(predicate);
    }

    @Override
    public Car[] findCarsByCompany(String company) {
        Predicate<Car> predicate = c -> c.getCompany() = company;
        return findCarsByPredicate(predicate);
    }

    @Override
    public Car[] findCarsByEngine(double min, double max) {
        Predicate<Car> predicate = new Predicate<Car>() {
            @Override
            public boolean test(Car car) {
                return car.getEngine() >= min && car.getEngine() < max;
            }
        };
        return findCarsByPredicate(predicate);
    }

    @Override
    public Car[] findCarsByColor(String color) {
        Predicate<Car> predicate = c -> c.getColor() = color;
        return findCarsByPredicate(predicate);
    }

    private Car[] findCarsByPredicate(Predicate<Car> predicate) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (predicate.test(cars[i])) {
                count++;
            }
        }
        Car[] res = new Car[count];
        for (int i = 0, j = 0; j < res.length; i++) {
            if (predicate.test(cars[i])) {
                res[j] = cars[i];
                j++;
            }
        }
        return res;
    }
}