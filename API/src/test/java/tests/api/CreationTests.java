package tests.api;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import utils.RestAssuredUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.AssertionsUtils;
import utils.data.api.SortUtils;

@Epic("Тестирование API тестового полигона")
public class CreationTests extends BaseTest {

    @Feature("Создание пользователя")
    @Test
    @DisplayName("API-T-003")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тестирование создания пользователя")
    @Issue("API-customer-create-user")
    public void createUserTest() {
        var answerApiUser = RestAssuredUtils.createUser(newLocalUser);

        Assertions.assertAll(
                () -> Assertions.assertEquals(newLocalUser.getFirstName(), answerApiUser.getFirstName()),
                () -> Assertions.assertEquals(newLocalUser.getSecondName(), answerApiUser.getSecondName()),
                () -> Assertions.assertEquals(newLocalUser.getAge(), answerApiUser.getAge()),
                () -> Assertions.assertEquals(newLocalUser.getSex(), answerApiUser.getSex()),
                () -> Assertions.assertEquals(newLocalUser.getMoney(), answerApiUser.getMoney())
        );

        var dbUser = userService.findUser(answerApiUser.getId());
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
    @Test
    @DisplayName("API-T-004")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тестирование создания дома с парковками")
    @Issue("API-customer-create-house")
    public void createHouseTest() {
        var answerApiHouse = RestAssuredUtils.createHouse(newLocalHouse);

        var expectedParking = SortUtils.sortParkingPlace(newLocalHouse);
        var actualParking = SortUtils.sortParkingPlace(answerApiHouse);

        Assertions.assertAll(
                () -> Assertions.assertEquals(newLocalHouse.getFloorCount(), answerApiHouse.getFloorCount()),
                () -> Assertions.assertEquals(newLocalHouse.getPrice(), answerApiHouse.getPrice())
        );

        AssertionsUtils.assertApiResponseMatchesRequest(expectedParking, actualParking);

        var dbHouse = houseService.findHouseWithParkingPlaces(answerApiHouse.getId());
        var dbParking = SortUtils.sortParkingPlace(dbHouse);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(dbHouse),
                () -> Assertions.assertEquals(newLocalHouse.getFloorCount(), dbHouse.getFloorCount()),
                () -> Assertions.assertEquals(newLocalHouse.getPrice(), dbHouse.getPrice())
        );

        AssertionsUtils.assertDbMatchesApi(actualParking, dbParking);
    }

    @Feature("Создание автомобиля")
    @Test
    @DisplayName("API-T-005")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Тестирование создания автомобиля")
    @Issue("API-customer-create-car")
    public void createCarTest() {
        var answerApiCar = RestAssuredUtils.createCar(newLocalCar);
        var dbCar = carService.findCar(answerApiCar.getId());

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
