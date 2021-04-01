package common.utils;

public final class UserSession {

    private static UserSession instance;
    private String email;
    private Authorization authorization;

    private UserSession(String email, Authorization authorization) {
        this.email = email;
        this.authorization = authorization;
    }


    public static UserSession createInstance(String userName, Authorization authorization) {
        if (instance == null) {
            instance = new UserSession(userName, authorization);
        }
        return instance;
    }

    public static  UserSession getInstance() {
        return instance;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public String getEmail() {
        return email;
    }

    public static enum Role {
        Admin,
        User,
        Both
    }

}