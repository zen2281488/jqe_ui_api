package utils.data.db;

import lombok.experimental.UtilityClass;
import models.api.Car;
import models.api.House;
import models.api.ParkingPlace;
import models.api.User;
import utils.properties.ConfProperties;

import java.util.List;

@UtilityClass
public class TestData {

    public User user() {
        return User.builder()
                .firstName(ConfProperties.string("firstName"))
                .secondName(ConfProperties.string("secondName"))
                .sex(ConfProperties.string("sex"))
                .age(ConfProperties.integer("age"))
                .money(ConfProperties.integer("money"))
                .build();
    }

    public Car car() {
        return Car.builder()
                .engineType(ConfProperties.string("engineType"))
                .mark(ConfProperties.string("mark"))
                .model(ConfProperties.string("model"))
                .price(ConfProperties.integer("carPrice"))
                .build();
    }

    public House house() {
        List<ParkingPlace> parkingPlaces = List.of(
                ParkingPlace.builder()
                        .isWarm(ConfProperties.bool("firstIsWarm"))
                        .isCovered(ConfProperties.bool("firstIsCovered"))
                        .placesCount(ConfProperties.integer("firstPlacesCount"))
                        .build(),
                ParkingPlace.builder()
                        .isWarm(ConfProperties.bool("secondIsWarm"))
                        .isCovered(ConfProperties.bool("secondIsCovered"))
                        .placesCount(ConfProperties.integer("secondPlacesCount"))
                        .build(),
                ParkingPlace.builder()
                        .isWarm(ConfProperties.bool("thirdIsWarm"))
                        .isCovered(ConfProperties.bool("thirdIsCovered"))
                        .placesCount(ConfProperties.integer("thirdPlacesCount"))
                        .build(),
                ParkingPlace.builder()
                        .isWarm(ConfProperties.bool("fourIsWarm"))
                        .isCovered(ConfProperties.bool("fourIsCovered"))
                        .placesCount(ConfProperties.integer("fourPlacesCount"))
                        .build()
        );

        return House.builder()
                .floorCount(ConfProperties.integer("floorCount"))
                .price(ConfProperties.integer("housePrice"))
                .parkingPlaces(parkingPlaces)
                .build();
    }
}

