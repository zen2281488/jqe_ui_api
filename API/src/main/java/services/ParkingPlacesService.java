package services;

import io.qameta.allure.Step;
import models.db.ParkingPlace;
import utils.db.DbUtils;

import java.util.List;

public class ParkingPlacesService {

    public ParkingPlacesService() {
    }

    @Step("Найти парковочное место по ID: {id}")
    public ParkingPlace findPlace(Integer id) {
        return DbUtils.findById(ParkingPlace.class, id);
    }

    @Step("Сохранить парковочное место в БД")
    public void savePlace(ParkingPlace place) {
        DbUtils.save(place);
    }

    @Step("Удалить парковочное место из БД")
    public void deletePlace(ParkingPlace place) {
        DbUtils.delete(place);
    }

    @Step("Обновить данные парковочного места в БД")
    public void updatePlace(ParkingPlace place) {
        DbUtils.update(place);
    }

    @Step("Найти все парковочные места в БД")
    public List<ParkingPlace> findAllPlaces() {
        return DbUtils.findAll(ParkingPlace.class);
    }

    @Step("Найти все парковочные места по ID дома: {id}")
    public List<ParkingPlace> findAllPlacesByHouseId(Integer id) {
        return DbUtils.findAll(ParkingPlace.class, id, "house_id");
    }
}
