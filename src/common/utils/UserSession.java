package common.utils;

public final class UserSession {

    private static UserSession instance;
    private String email;
    private String role;

    private UserSession(String email, Role role) {
        this.email = email;
        this.role = role.toString();
    }


    public static UserSession createInstance(String userName, Role role) {
        if (instance == null) {
            instance = new UserSession(userName, role);
        }
        return instance;
    }

    public static  UserSession getInstance() {
        return instance;
    }

    public String getRole() {
        return role;
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