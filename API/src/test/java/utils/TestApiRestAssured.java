package utils;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.api.Car;
import models.api.House;
import models.api.User;
import org.json.JSONObject;

public class TestApiRestAssured {

    private static String token;

    @Step("Получение токена авторизации")
    public static String getToken() {
        token = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new JSONObject()
                        .put("username", System.getProperty("API_USER"))
                        .put("password", System.getProperty("API_PASSWORD"))
                        .toString())
                .when()
                .post(System.getProperty("BASE_URL") + "login")
                .then()
                .statusCode(202)
                .extract()
                .path("access_token")
                .toString();
        return token;
    }

    @Step("Формирование RestAssured спецификации с токеном")
    public static RequestSpecification getRestAssuredSpecification() {
        return RestAssured.given()
                .headers(
                        "Authorization", "Bearer " + token,
                        "Content-Type", ContentType.JSON,
                        "Accept", ContentType.JSON
                );
    }

    @Step("Создание пользователя через API")
    public static User createUser(User newUser) {
        return getRestAssuredSpecification()
                .body(newUser)
                .when()
                .post(RestAssured.baseURI + "user")
                .then()
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract()
                .as(User.class);
    }

    @Step("Создание дома через API")
    public static House createHouse(House newHouse) {
        return getRestAssuredSpecification()
                .body(newHouse)
                .when()
                .post(RestAssured.baseURI + "house")
                .then()
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract()
                .as(House.class);
    }

    @Step("Создание автомобиля через API")
    public static Car createCar(Car newCar) {
        return getRestAssuredSpecification()
                .body(newCar)
                .when()
                .post(RestAssured.baseURI + "car")
                .then()
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract()
                .as(Car.class);
    }

    @Step("Покупка автомобиля пользователем. UserID: {userId}, CarID: {carId}")
    public static User buyCar(Integer userId, Integer carId) {
        return getRestAssuredSpecification()
                .when()
                .post(RestAssured.baseURI + "user/" + userId + "/buyCar/" + carId)
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .as(User.class);
    }

    @Step("Покупка дома пользователем. HouseID: {houseId}, UserID: {userId}")
    public static House buyHouse(Integer houseId, Integer userId) {
        return getRestAssuredSpecification()
                .when()
                .post(RestAssured.baseURI + "house/" + houseId + "/settle/" + userId)
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .as(House.class);
    }

}
