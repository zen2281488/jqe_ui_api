package tests.api;

import io.restassured.config.ObjectMapperConfig;
import io.restassured.mapper.ObjectMapperType;
import org.junit.jupiter.api.BeforeEach;
import services.CarService;
import services.HouseService;
import services.UserService;
import utils.ApiClientUtils;
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
        RestAssured.config = RestAssured.config().objectMapperConfig(
                ObjectMapperConfig.objectMapperConfig()
                        .defaultObjectMapperType(ObjectMapperType.JACKSON_2)
        );
    }

    @BeforeEach
    public void beforeEach() {
        token = ApiClientUtils.getToken();
        newLocalUser = TestData.user();
        newLocalHouse = TestData.house();
        newLocalCar = TestData.car();

        userService = new UserService();
        houseService = new HouseService();
        carService = new CarService();

    }
}
