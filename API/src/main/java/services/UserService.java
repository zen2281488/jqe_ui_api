package services;

import io.qameta.allure.Step;
import models.db.User;
import utils.db.DbDao;

import java.util.List;

public class UserService {

    private final DbDao usersDao = new DbDao();

    public UserService() {
    }

    @Step("Найти пользователя по ID: {id}")
    public User findUser(int id) {
        return usersDao.findById(User.class, id);
    }

    @Step("Сохранить пользователя в БД")
    public void saveUser(User user) {
        usersDao.save(user);
    }

    @Step("Удалить пользователя из БД")
    public void deleteUser(User user) {
        usersDao.delete(user);
    }

    @Step("Обновить данные пользователя в БД")
    public void updateUser(User user) {
        usersDao.update(user);
    }

    @Step("Найти всех пользователей в БД")
    public List<User> findAllUsers() {
        return usersDao.findAll(User.class);
    }
}
