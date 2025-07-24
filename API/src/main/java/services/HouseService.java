package services;

import io.qameta.allure.Step;
import models.db.House;
import org.hibernate.Session;
import utils.db.DbUtils;
import utils.db.HibernateSessionFactoryUtil;

import java.util.List;

public class HouseService {

    public HouseService() {
    }

    @Step("Найти дом по ID: {id}")
    public House findHouse(int id) {
        return DbUtils.findById(House.class, id);
    }

    @Step("Найти дом с парковочными местами по ID: {id}")
    public House findHouseWithParkingPlaces(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT h FROM House h LEFT JOIN FETCH h.parkingPlaces WHERE h.id = :id", House.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Не удалось найти дом с парковками по ID: " + id, e);
        }
    }


    @Step("Сохранить дом в БД")
    public void saveHouse(House house) {
        DbUtils.save(house);
    }

    @Step("Удалить дом из БД")
    public void deleteHouse(House house) {
        DbUtils.delete(house);
    }

    @Step("Обновить данные дома в БД")
    public void updateHouse(House house) {
        DbUtils.update(house);
    }

    @Step("Найти все дома в БД")
    public List<House> findAllHouses() {
        return DbUtils.findAll(House.class);
    }
}
