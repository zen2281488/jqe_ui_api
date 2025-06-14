package services;

import io.qameta.allure.Step;
import models.db.Car;
import utils.db.DbDao;

import java.util.List;

public class CarService {

    private final DbDao carsDao = new DbDao();

    public CarService() {
    }

    @Step("Найти автомобиль по ID: {id}")
    public Car findCar(int id) {
        return carsDao.findById(Car.class, id);
    }

    @Step("Сохранить автомобиль в БД")
    public void saveCar(Car car) {
        carsDao.save(car);
    }

    @Step("Удалить автомобиль из БД")
    public void deleteCar(Car car) {
        carsDao.delete(car);
    }

    @Step("Обновить данные автомобиля в БД")
    public void updateCar(Car car) {
        carsDao.update(car);
    }

    @Step("Найти все автомобили в БД")
    public List<Car> findAllCars() {
        return carsDao.findAll(Car.class);
    }
}
