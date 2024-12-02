package full.stack.parkspring.config;

import full.stack.parkspring.model.AppUser;
import lombok.Getter;

@Getter
public class UserSession {


    private static volatile UserSession instance;

    // Getter for logged-in user
    // Session user
    private AppUser loggedInUser;


    private UserSession() {}


    public static UserSession getInstance() {
        if (instance == null) {
            synchronized (UserSession.class) {
                if (instance == null) {
                    instance = new UserSession();
                }
            }
        }
        return instance;
    }

    // Setter for logged-in user
    public void setLoggedInUser(AppUser user) {
        this.loggedInUser = user;
    }

    // Clear session
    public void clearSession() {
        this.loggedInUser = null;
    }
}
