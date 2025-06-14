package services;

import io.qameta.allure.Step;
import models.db.ParkingPlace;
import utils.db.DbDao;

import java.util.List;

public class ParkingPlacesService {

    private final DbDao placesDao = new DbDao();

    public ParkingPlacesService() {
    }

    @Step("Найти парковочное место по ID: {id}")
    public ParkingPlace findPlace(Integer id) {
        return placesDao.findById(ParkingPlace.class, id);
    }

    @Step("Сохранить парковочное место в БД")
    public void savePlace(ParkingPlace place) {
        placesDao.save(place);
    }

    @Step("Удалить парковочное место из БД")
    public void deletePlace(ParkingPlace place) {
        placesDao.delete(place);
    }

    @Step("Обновить данные парковочного места в БД")
    public void updatePlace(ParkingPlace place) {
        placesDao.update(place);
    }

    @Step("Найти все парковочные места в БД")
    public List<ParkingPlace> findAllPlaces() {
        return placesDao.findAll(ParkingPlace.class);
    }

    @Step("Найти все парковочные места по ID дома: {id}")
    public List<ParkingPlace> findAllPlacesByHouseId(Integer id) {
        return placesDao.findAll(ParkingPlace.class, id, "house_id");
    }
}
