package services;

import io.qameta.allure.Step;
import models.db.Car;
import utils.db.DbUtils;

import java.util.List;

public class CarService {

    public CarService() {
    }

    @Step("Найти автомобиль по ID: {id}")
    public Car findCar(int id) {
        return DbUtils.findById(Car.class, id);
    }

    @Step("Сохранить автомобиль в БД")
    public void saveCar(Car car) {
        DbUtils.save(car);
    }

    @Step("Удалить автомобиль из БД")
    public void deleteCar(Car car) {
        DbUtils.delete(car);
    }

    @Step("Обновить данные автомобиля в БД")
    public void updateCar(Car car) {
        DbUtils.update(car);
    }

    @Step("Найти все автомобили в БД")
    public List<Car> findAllCars() {
        return DbUtils.findAll(Car.class);
    }
}
