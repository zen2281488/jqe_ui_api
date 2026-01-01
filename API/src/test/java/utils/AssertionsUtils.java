package utils;

import lombok.experimental.UtilityClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class AssertionsUtils {

    public static void assertApiResponseMatchesRequest(List<models.api.ParkingPlace> expected, List<models.api.ParkingPlace> fromApi) {
        Assertions.assertEquals(expected.size(), fromApi.size(), "Ответ API: количество парковочных мест не совпадает");

        List<Executable> assertions = new ArrayList<>();
        for (int i = 0; i < expected.size(); i++) {
            var exp = expected.get(i);
            var api = fromApi.get(i);

            assertions.add(() -> Assertions.assertEquals(exp.isWarm(), api.isWarm(), "Ответ API → isWarm"));
            assertions.add(() -> Assertions.assertEquals(exp.isCovered(), api.isCovered(), "Ответ API → isCovered"));
            assertions.add(() -> Assertions.assertEquals(exp.getPlacesCount(), api.getPlacesCount(), "Ответ API → placesCount"));
        }

        Assertions.assertAll(assertions);
    }

    public static void assertDbMatchesApi(List<models.api.ParkingPlace> expected, List<models.db.ParkingPlace> fromDb) {
        Assertions.assertEquals(expected.size(), fromDb.size(), "БД: количество парковочных мест не совпадает");

        List<Executable> assertions = new ArrayList<>();
        for (int i = 0; i < expected.size(); i++) {
            var exp = expected.get(i);
            var db = fromDb.get(i);

            assertions.add(() -> Assertions.assertEquals(exp.isWarm(), db.isWarm(), "БД → isWarm"));
            assertions.add(() -> Assertions.assertEquals(exp.isCovered(), db.isCovered(), "БД → isCovered"));
            assertions.add(() -> Assertions.assertEquals(exp.getPlacesCount(), db.getPlacesCount(), "БД → placesCount"));
        }

        Assertions.assertAll(assertions);
    }
}
