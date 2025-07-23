package utils;

import io.vavr.collection.Stream;
import lombok.experimental.UtilityClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

@UtilityClass
public class AssertionsUtils {

    public static void assertApiResponseMatchesRequest(List<models.api.ParkingPlace> expected, List<models.api.ParkingPlace> fromApi) {
        Assertions.assertEquals(expected.size(), fromApi.size(), "Ответ API: количество парковочных мест не совпадает");

        List<Executable> assertions = Stream.ofAll(expected)
                .zip(fromApi)
                .flatMap(pair -> {
                    var exp = pair._1;
                    var api = pair._2;
                    return Stream.of(
                            (Executable) () -> Assertions.assertEquals(exp.isWarm(), api.isWarm(), "Ответ API → isWarm"),
                            (Executable) () -> Assertions.assertEquals(exp.isCovered(), api.isCovered(), "Ответ API → isCovered"),
                            (Executable) () -> Assertions.assertEquals(exp.getPlacesCount(), api.getPlacesCount(), "Ответ API → placesCount")
                    );
                })
                .toJavaList();

        Assertions.assertAll(assertions);
    }

    public static void assertDbMatchesApi(List<models.api.ParkingPlace> expected, List<models.db.ParkingPlace> fromDb) {
        Assertions.assertEquals(expected.size(), fromDb.size(), "БД: количество парковочных мест не совпадает");

        List<Executable> assertions = Stream.ofAll(expected)
                .zip(fromDb)
                .flatMap(pair -> {
                    var exp = pair._1;
                    var db = pair._2;
                    return Stream.of(
                            (Executable) () -> Assertions.assertEquals(exp.isWarm(), db.isWarm(), "БД → isWarm"),
                            (Executable) () -> Assertions.assertEquals(exp.isCovered(), db.isCovered(), "БД → isCovered"),
                            (Executable) () -> Assertions.assertEquals(exp.getPlacesCount(), db.getPlacesCount(), "БД → placesCount")
                    );
                })
                .toJavaList();

        Assertions.assertAll(assertions);
    }
}
