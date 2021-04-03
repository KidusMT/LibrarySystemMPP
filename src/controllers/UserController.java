package controllers;

import common.utils.DataAccess;
import common.utils.DataAccessFacade;
import daos.UserDAO;
import models.User;

import java.util.HashMap;

public class UserController {
    private UserDAO userDAO;

    public UserController() {
        userDAO = new UserDAO();
    }

    public User authenticateUser(String userId, String password) {
        DataAccess dataAccess = new DataAccessFacade();
        HashMap<String, User> users = dataAccess.readUserMap();
        for (String id : users.keySet()) {
            if (userId.equals(id) && users.get(id).getPassword().equals(password))
                return users.get(id);
        }
        return null;
    }


}
