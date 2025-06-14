package services;

import io.qameta.allure.Step;
import models.db.House;
import org.hibernate.Session;
import utils.db.DbDao;
import utils.db.HibernateSessionFactoryUtil;

import java.util.List;

public class HouseService {

    private final DbDao housesDao = new DbDao();

    public HouseService() {
    }

    @Step("Найти дом по ID: {id}")
    public House findHouse(int id) {
        return housesDao.findById(House.class, id);
    }

    @Step("Найти дом с парковочными местами по ID: {id}")
    public House findHouseWithParkingPlaces(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT h FROM House h LEFT JOIN FETCH h.parkingPlaces WHERE h.id = :id", House.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Step("Сохранить дом в БД")
    public void saveHouse(House house) {
        housesDao.save(house);
    }

    @Step("Удалить дом из БД")
    public void deleteHouse(House house) {
        housesDao.delete(house);
    }

    @Step("Обновить данные дома в БД")
    public void updateHouse(House house) {
        housesDao.update(house);
    }

    @Step("Найти все дома в БД")
    public List<House> findAllHouses() {
        return housesDao.findAll(House.class);
    }
}
