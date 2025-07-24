package utils.data.api;

import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;
import models.api.House;
import models.api.ParkingPlace;

import java.util.Comparator;
import java.util.List;

@UtilityClass
public class SortUtils {

    @Step("Сортировка списка парковочных мест для API модели дома")
    public List<ParkingPlace> sortParkingPlace(House house) {
        return house.getParkingPlaces()
                .stream()
                .sorted(Comparator.comparingInt(ParkingPlace::getPlacesCount))
                .toList();
    }

    @Step("Сортировка списка парковочных мест для DB модели дома")
    public List<models.db.ParkingPlace> sortParkingPlace(models.db.House house) {
        return house.getParkingPlaces()
                .stream()
                .sorted(Comparator.comparingInt(models.db.ParkingPlace::getPlacesCount))
                .toList();
    }
}
