package tests.api;

import utils.TestApiRestAssured;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import models.api.Car;
import models.api.House;
import models.api.User;
import org.junit.jupiter.api.BeforeAll;
import utils.data.db.Create;

public abstract class BaseTest {
    protected static String token;
    protected static User newLocalUser;
    protected static House newLocalHouse;
    protected static Car newLocalCar;

    static {
        RestAssured.baseURI = System.getProperty("BASE_URL");
        RestAssured.defaultParser = Parser.JSON;
    }

    @BeforeAll
    public static void beforeAll() {
        token = TestApiRestAssured.getToken();
        newLocalUser = Create.createUser();
        newLocalHouse = Create.createHouse();
        newLocalCar = Create.createCar();
    }
}
