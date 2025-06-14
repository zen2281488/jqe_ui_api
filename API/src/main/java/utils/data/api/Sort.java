package utils.data.api;

import io.qameta.allure.Step;
import models.api.House;
import models.api.ParkingPlace;

import java.util.Comparator;
import java.util.List;

public class Sort {

    @Step("Сортировка списка парковочных мест для API модели дома")
    public static List<ParkingPlace> sortParkingPlace(House house) {
        return house.getParkingPlaces()
                .stream()
                .sorted(Comparator.comparingInt(ParkingPlace::getPlacesCount))
                .toList();
    }

    @Step("Сортировка списка парковочных мест для DB модели дома")
    public static List<models.db.ParkingPlace> sortParkingPlace(models.db.House house) {
        return house.getParkingPlaces()
                .stream()
                .sorted(Comparator.comparingInt(models.db.ParkingPlace::getPlacesCount))
                .toList();
    }
}
