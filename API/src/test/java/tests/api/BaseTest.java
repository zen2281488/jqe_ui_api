package tests.api;

import org.junit.jupiter.api.BeforeEach;
import services.CarService;
import services.HouseService;
import services.UserService;
import utils.RestAssuredUtils;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import models.api.Car;
import models.api.House;
import models.api.User;
import utils.data.db.TestData;

public abstract class BaseTest {
    protected String token;
    protected User newLocalUser;
    protected House newLocalHouse;
    protected Car newLocalCar;
    protected UserService userService;
    protected HouseService houseService;
    protected CarService carService;

    static {
        RestAssured.baseURI = System.getProperty("BASE_URL");
        RestAssured.defaultParser = Parser.JSON;
    }

    @BeforeEach
    public void beforeEach() {
        token = RestAssuredUtils.getToken();
        newLocalUser = TestData.user();
        newLocalHouse = TestData.house();
        newLocalCar = TestData.car();

        userService = new UserService();
        houseService = new HouseService();
        carService = new CarService();

    }
}
