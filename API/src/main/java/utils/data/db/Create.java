package utils.data.db;

import models.api.Car;
import models.api.House;
import models.api.ParkingPlace;
import models.api.User;
import utils.properties.ConfProperties;

import java.util.ArrayList;
import java.util.List;

public class Create {

    public static User createUser(){
        return User.builder()
                .firstName(ConfProperties.getCommonProperty("firstName"))
                .secondName(ConfProperties.getCommonProperty("secondName"))
                .sex(ConfProperties.getCommonProperty("sex"))
                .age(ConfProperties.getCommonIntProperty("age"))
                .money(ConfProperties.getCommonIntProperty("money"))
                .build();
    }

    public static Car createCar(){
        return Car
                .builder()
                .engineType(ConfProperties.getCommonProperty("engineType"))
                .mark(ConfProperties.getCommonProperty("mark"))
                .model(ConfProperties.getCommonProperty("model"))
                .price(ConfProperties.getCommonIntProperty("carPrice"))
                .build();
    }

    public static House createHouse(){
        ParkingPlace firstParkingPlace = ParkingPlace.builder().isWarm(ConfProperties.getCommonBoolProperty("firstIsWarm")).isCovered(ConfProperties.getCommonBoolProperty("firstIsCovered")).placesCount(ConfProperties.getCommonIntProperty("firstPlacesCount")).build();
        ParkingPlace secondParkingPlace = ParkingPlace.builder().isWarm(ConfProperties.getCommonBoolProperty("secondIsWarm")).isCovered(ConfProperties.getCommonBoolProperty("secondIsCovered")).placesCount(ConfProperties.getCommonIntProperty("secondPlacesCount")).build();
        ParkingPlace thirdParkingPlace = ParkingPlace.builder().isWarm(ConfProperties.getCommonBoolProperty("thirdIsWarm")).isCovered(ConfProperties.getCommonBoolProperty("thirdIsCovered")).placesCount(ConfProperties.getCommonIntProperty("thirdPlacesCount")).build();
        ParkingPlace fourParkingPlace = ParkingPlace.builder().isWarm(ConfProperties.getCommonBoolProperty("fourIsWarm")).isCovered(ConfProperties.getCommonBoolProperty("fourIsCovered")).placesCount(ConfProperties.getCommonIntProperty("fourPlacesCount")).build();

        List<ParkingPlace> parkingPlaces = new ArrayList<>();
        parkingPlaces.add(firstParkingPlace);
        parkingPlaces.add(secondParkingPlace);
        parkingPlaces.add(thirdParkingPlace);
        parkingPlaces.add(fourParkingPlace);

        return House.builder()
                .floorCount(ConfProperties.getCommonIntProperty("floorCount"))
                .price(ConfProperties.getCommonIntProperty("housePrice"))
                .parkingPlaces(parkingPlaces)
                .build();
    }


}
