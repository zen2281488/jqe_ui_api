package tests.api;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import utils.AssertionsUtils;
import utils.ApiClientUtils;
import utils.data.api.SortUtils;

@Epic("Тестирование API тестового полигона")
public class BuySellTests extends BaseTest {

    @Feature("Покупка автомобиля")
    @Description("Тестирование покупки автомобиля")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("API-customer-buy-car")
    @DisplayName("API-T-001")
    @Test
    public void buyCarTest() {
        var answerApiCar = ApiClientUtils.createCar(newLocalCar);
        var answerBuyCar = ApiClientUtils.buyCar(ApiClientUtils.createUser(newLocalUser).getId(), answerApiCar.getId());
        var dbUser = userService.findUser(answerBuyCar.getId());

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
        var answerApiUser = ApiClientUtils.createUser(newLocalUser);
        var answerApiHouse = ApiClientUtils.createHouse(newLocalHouse);
        var answerBuyHouse = ApiClientUtils.buyHouse(answerApiHouse.getId(), answerApiUser.getId());

        var expectedParking = SortUtils.sortParkingPlace(newLocalHouse);
        var apiParking = SortUtils.sortParkingPlace(answerBuyHouse);

        var dbHouse = houseService.findHouseWithParkingPlaces(answerApiHouse.getId());
        var dbParking = SortUtils.sortParkingPlace(dbHouse);

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

        AssertionsUtils.assertApiResponseMatchesRequest(expectedParking, apiParking);
        AssertionsUtils.assertDbMatchesApi(expectedParking, dbParking);
    }
}
