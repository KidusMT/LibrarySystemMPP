package controllers;

import daos.UserDAO;
import models.User;

public class UserController {
    private UserDAO userDAO;

    public UserController() {
        userDAO = new UserDAO();
    }

    public User authenticateUser(String userId,String password){
        // TODO: implement
        return new User("","","","");
    }



}
