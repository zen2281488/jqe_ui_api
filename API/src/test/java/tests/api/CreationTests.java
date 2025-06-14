package tests.api;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import util.api.TestApiRestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import services.CarService;
import services.HouseService;
import services.UserService;
import utils.data.api.Sort;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreationTests extends BaseTest {

    @Feature("Создание пользователя")
    @Description("Тестирование создания пользователя")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("API-customer-create-user")
    @DisplayName("API-T-003")
    @Test
    public void createUserTest() {
        var answerApiUser = TestApiRestAssured.createUser(newLocalUser);

        Assertions.assertAll(
                () -> Assertions.assertEquals(newLocalUser.getFirstName(), answerApiUser.getFirstName()),
                () -> Assertions.assertEquals(newLocalUser.getSecondName(), answerApiUser.getSecondName()),
                () -> Assertions.assertEquals(newLocalUser.getAge(), answerApiUser.getAge()),
                () -> Assertions.assertEquals(newLocalUser.getSex(), answerApiUser.getSex()),
                () -> Assertions.assertEquals(newLocalUser.getMoney(), answerApiUser.getMoney())
        );

        var dbUser = new UserService().findUser(answerApiUser.getId());
        Assertions.assertAll(
                () -> Assertions.assertNotNull(dbUser),
                () -> Assertions.assertEquals(answerApiUser.getFirstName(), dbUser.getFirstName()),
                () -> Assertions.assertEquals(answerApiUser.getSecondName(), dbUser.getSecondName()),
                () -> Assertions.assertEquals(answerApiUser.getAge(), dbUser.getAge()),
                () -> Assertions.assertEquals(answerApiUser.getSex(), dbUser.getSex()),
                () -> Assertions.assertEquals(answerApiUser.getMoney(), dbUser.getMoney()),
                () -> Assertions.assertEquals(answerApiUser.getId(), dbUser.getId())
        );
    }

    @Feature("Создание дома")
    @Description("Тестирование создания дома с парковками")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("API-customer-create-house")
    @DisplayName("API-T-004")
    @Test
    public void createHouseTest() {
        var answerApiHouse = TestApiRestAssured.createHouse(newLocalHouse);
        var newHouseParkingPlaces = Sort.sortParkingPlace(newLocalHouse);
        var answerApiHouseParkingPlaces = Sort.sortParkingPlace(answerApiHouse);
        var dbHouse = new HouseService().findHouseWithParkingPlaces(answerApiHouse.getId());
        var dbHouseParkingPlaces = Sort.sortParkingPlace(dbHouse);

        Assertions.assertAll(
                () -> Assertions.assertEquals(newLocalHouse.getFloorCount(), answerApiHouse.getFloorCount()),
                () -> Assertions.assertEquals(newLocalHouse.getPrice(), answerApiHouse.getPrice())
        );

        Assertions.assertAll(IntStream.range(0, newHouseParkingPlaces.size())
                .boxed()
                .flatMap(i -> Stream.of(
                        () -> Assertions.assertEquals(
                                newHouseParkingPlaces.get(i).isWarm(),
                                answerApiHouseParkingPlaces.get(i).isWarm()
                        ),
                        () -> Assertions.assertEquals(
                                newHouseParkingPlaces.get(i).isCovered(),
                                answerApiHouseParkingPlaces.get(i).isCovered()
                        ),
                        (Executable) () -> Assertions.assertEquals(
                                newHouseParkingPlaces.get(i).getPlacesCount(),
                                answerApiHouseParkingPlaces.get(i).getPlacesCount()
                        )
                ))
                .collect(Collectors.toList()));

        Assertions.assertAll(
                () -> Assertions.assertNotNull(dbHouse),
                () -> Assertions.assertEquals(newLocalHouse.getFloorCount(), dbHouse.getFloorCount()),
                () -> Assertions.assertEquals(newLocalHouse.getPrice(), dbHouse.getPrice())
        );

        Assertions.assertAll(IntStream.range(0, newHouseParkingPlaces.size())
                .boxed()
                .flatMap(i -> Stream.of(
                        () -> Assertions.assertEquals(
                                answerApiHouseParkingPlaces.get(i).isWarm(),
                                dbHouseParkingPlaces.get(i).isWarm()
                        ),
                        () -> Assertions.assertEquals(
                                answerApiHouseParkingPlaces.get(i).isCovered(),
                                dbHouseParkingPlaces.get(i).isCovered()
                        ),
                        (Executable) () -> Assertions.assertEquals(
                                answerApiHouseParkingPlaces.get(i).getPlacesCount(),
                                dbHouseParkingPlaces.get(i).getPlacesCount()
                        )
                ))
                .collect(Collectors.toList()));
    }

    @Feature("Создание автомобиля")
    @Description("Тестирование создания автомобиля")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("API-customer-create-car")
    @DisplayName("API-T-005")
    @Test
    public void createCarTest() {
        var answerApiCar = TestApiRestAssured.createCar(newLocalCar);
        var dbCar = new CarService().findCar(answerApiCar.getId());

        Assertions.assertAll(
                () -> Assertions.assertEquals(newLocalCar.getEngineType(), answerApiCar.getEngineType()),
                () -> Assertions.assertEquals(newLocalCar.getMark(), answerApiCar.getMark()),
                () -> Assertions.assertEquals(newLocalCar.getModel(), answerApiCar.getModel()),
                () -> Assertions.assertEquals(newLocalCar.getPrice(), answerApiCar.getPrice())
        );

        Assertions.assertAll(
                () -> Assertions.assertNotNull(dbCar),
                () -> Assertions.assertEquals(answerApiCar.getEngineType(), dbCar.getEngineType().getTypeName()),
                () -> Assertions.assertEquals(answerApiCar.getMark(), dbCar.getMark()),
                () -> Assertions.assertEquals(answerApiCar.getModel(), dbCar.getModel()),
                () -> Assertions.assertEquals(answerApiCar.getPrice(), dbCar.getPrice())
        );
    }

}
