package utils.data.db;

import lombok.experimental.UtilityClass;
import models.api.Car;
import models.api.House;
import models.api.ParkingPlace;
import models.api.User;
import utils.properties.Config;

import java.util.List;

@UtilityClass
public class TestData {

    public User user() {
        return User.builder()
                .firstName(Config.string("firstName"))
                .secondName(Config.string("secondName"))
                .sex(Config.string("sex"))
                .age(Config.integer("age"))
                .money(Config.integer("money"))
                .build();
    }

    public Car car() {
        return Car.builder()
                .engineType(Config.string("engineType"))
                .mark(Config.string("mark"))
                .model(Config.string("model"))
                .price(Config.integer("carPrice"))
                .build();
    }

    public House house() {
        List<ParkingPlace> parkingPlaces = List.of(
                ParkingPlace.builder()
                        .isWarm(Config.bool("firstIsWarm"))
                        .isCovered(Config.bool("firstIsCovered"))
                        .placesCount(Config.integer("firstPlacesCount"))
                        .build(),
                ParkingPlace.builder()
                        .isWarm(Config.bool("secondIsWarm"))
                        .isCovered(Config.bool("secondIsCovered"))
                        .placesCount(Config.integer("secondPlacesCount"))
                        .build(),
                ParkingPlace.builder()
                        .isWarm(Config.bool("thirdIsWarm"))
                        .isCovered(Config.bool("thirdIsCovered"))
                        .placesCount(Config.integer("thirdPlacesCount"))
                        .build(),
                ParkingPlace.builder()
                        .isWarm(Config.bool("fourIsWarm"))
                        .isCovered(Config.bool("fourIsCovered"))
                        .placesCount(Config.integer("fourPlacesCount"))
                        .build()
        );

        return House.builder()
                .floorCount(Config.integer("floorCount"))
                .price(Config.integer("housePrice"))
                .parkingPlaces(parkingPlaces)
                .build();
    }
}

