package tests.api;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import util.api.TestApiRestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import services.HouseService;
import services.UserService;
import utils.data.api.Sort;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuySellTests extends BaseTest {

    @Feature("Покупка автомобиля")
    @Description("Тестирование покупки автомобиля")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("API-customer-buy-car")
    @DisplayName("API-T-001")
    @Test
    public void buyCarTest() {
        var answerApiCar = TestApiRestAssured.createCar(newLocalCar);
        var answerBuyCar = TestApiRestAssured.buyCar(TestApiRestAssured.createUser(newLocalUser).getId(), answerApiCar.getId());
        var dbUser = new UserService().findUser(answerBuyCar.getId());

        Assertions.assertAll(
                () -> Assertions.assertEquals(newLocalUser.getFirstName(), answerBuyCar.getFirstName()),
                () -> Assertions.assertEquals(newLocalUser.getSecondName(), answerBuyCar.getSecondName()),
                () -> Assertions.assertEquals(newLocalUser.getAge(), answerBuyCar.getAge()),
                () -> Assertions.assertEquals(newLocalUser.getSex(), answerBuyCar.getSex()),
                () -> Assertions.assertEquals(newLocalUser.getMoney() - answerApiCar.getPrice(), answerBuyCar.getMoney())
        );

        Assertions.assertAll(
                () -> Assertions.assertEquals(answerBuyCar.getFirstName(), dbUser.getFirstName()),
                () -> Assertions.assertEquals(answerBuyCar.getSecondName(), dbUser.getSecondName()),
                () -> Assertions.assertEquals(answerBuyCar.getAge(), dbUser.getAge()),
                () -> Assertions.assertEquals(answerBuyCar.getSex(), dbUser.getSex()),
                () -> Assertions.assertEquals(answerBuyCar.getMoney(), dbUser.getMoney()),
                () -> Assertions.assertEquals(answerBuyCar.getId(), dbUser.getId())
        );
    }

    @Feature("Заселение в дом")
    @Description("Тестирование заселения пользователя в дом")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("API-customer-settle-house")
    @DisplayName("API-T-002")
    @Test
    public void settleHouse() {
        var answerApiUser = TestApiRestAssured.createUser(newLocalUser);
        var answerApiHouse = TestApiRestAssured.createHouse(newLocalHouse);
        var answerBuyHouse = TestApiRestAssured.buyHouse(answerApiHouse.getId(), answerApiUser.getId());
        var newHouseParkingPlaces = Sort.sortParkingPlace(newLocalHouse);
        var answerBuyHouseParkingPlaces = Sort.sortParkingPlace(answerBuyHouse);
        var dbHouse = new HouseService().findHouseWithParkingPlaces(answerApiHouse.getId());
        var dbHouseParkingPlaces = Sort.sortParkingPlace(dbHouse);

        Assertions.assertAll(IntStream.range(0, newHouseParkingPlaces.size())
                .boxed()
                .flatMap(i -> Stream.of(
                        () -> Assertions.assertEquals(
                                newHouseParkingPlaces.get(i).isWarm(),
                                answerBuyHouseParkingPlaces.get(i).isWarm()
                        ),
                        () -> Assertions.assertEquals(
                                newHouseParkingPlaces.get(i).isCovered(),
                                answerBuyHouseParkingPlaces.get(i).isCovered()
                        ),
                        (Executable) () -> Assertions.assertEquals(
                                newHouseParkingPlaces.get(i).getPlacesCount(),
                                answerBuyHouseParkingPlaces.get(i).getPlacesCount()
                        )
                ))
                .collect(Collectors.toList()));

        Assertions.assertAll(
                () -> Assertions.assertEquals(newLocalUser.getFirstName(), answerBuyHouse.getLodgers().get(0).getFirstName()),
                () -> Assertions.assertEquals(newLocalUser.getSecondName(), answerBuyHouse.getLodgers().get(0).getSecondName()),
                () -> Assertions.assertEquals(newLocalUser.getAge(), answerBuyHouse.getLodgers().get(0).getAge()),
                () -> Assertions.assertEquals(newLocalUser.getSex(), answerBuyHouse.getLodgers().get(0).getSex()),
                () -> Assertions.assertEquals(newLocalUser.getMoney() - newLocalHouse.getPrice(), answerBuyHouse.getLodgers().get(0).getMoney())
        );

        Assertions.assertAll(
                () -> Assertions.assertEquals(answerBuyHouse.getFloorCount(), dbHouse.getFloorCount()),
                () -> Assertions.assertEquals(answerBuyHouse.getPrice(), dbHouse.getPrice())
        );

        Assertions.assertAll(IntStream.range(0, newHouseParkingPlaces.size())
                .boxed()
                .flatMap(i -> Stream.of(
                        () -> Assertions.assertEquals(
                                answerBuyHouseParkingPlaces.get(i).isWarm(),
                                dbHouseParkingPlaces.get(i).isWarm()
                        ),
                        () -> Assertions.assertEquals(
                                answerBuyHouseParkingPlaces.get(i).isCovered(),
                                dbHouseParkingPlaces.get(i).isCovered()
                        ),
                        (Executable) () -> Assertions.assertEquals(
                                answerBuyHouseParkingPlaces.get(i).getPlacesCount(),
                                dbHouseParkingPlaces.get(i).getPlacesCount()
                        )
                ))
                .collect(Collectors.toList()));
    }
}
