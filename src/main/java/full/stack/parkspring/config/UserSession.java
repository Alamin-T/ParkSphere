package full.stack.parkspring.config;

import full.stack.parkspring.model.AppUser;
import lombok.Getter;

@Getter
public class UserSession {
    private static UserSession instance;
    @Getter
    private static AppUser loggedInUser;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setLoggedInUser(AppUser user) {
        this.loggedInUser = user;
    }

    public void clearSession() {
        this.loggedInUser = null;
    }
}

