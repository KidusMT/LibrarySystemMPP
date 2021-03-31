package daos;

import common.interfaces.DAO;
import common.utils.FileStorage;
import models.User;

import java.util.List;

public class UserDAO implements DAO<User> {
    private FileStorage<User> userFileStorage;

    public UserDAO() {
        userFileStorage = new FileStorage<>();
    }

    @Override
    public User get(int id) {
        List<User> userList = getAll();
        User user = userList.stream().filter(u -> id == u.getId())
                .findAny()
                .orElse(null);
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = userFileStorage.listAll(FileStorage.StorageType.USER);
        return userList;
    }

    public User getByEmail(String email) {
        List<User> userList = getAll();
        User user = userList.stream().filter(u -> email.equals(u.getEmail()))
                .findAny()
                .orElse(null);
        return user;
    }

    @Override
    public User create(User entity) {
        userFileStorage.save(FileStorage.StorageType.USER, entity);
        return getByEmail(entity.getEmail());
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }
}
