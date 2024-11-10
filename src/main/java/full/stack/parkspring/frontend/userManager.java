package full.stack.parkspring.frontend;

import java.util.HashMap;
import java.util.Map;

public class userManager {

    private static Map<String, User> users = new HashMap<>();

    static {
        // Initialize with some default users
        users.put("kaz@gmail.com", new User("Kaz", "Smith", "kaz@gmail.com", "01/01/1990", "Male", "kaz123"));
        users.put("bob@gmail.com", new User("Bob", "Brown", "bob@gmail.com", "02/02/1990", "Male", "bob123"));
        users.put("john@gmail.com", new User("John", "Doe", "john@gmail.com", "03/03/1990", "Male", "john123"));
        users.put("jane@gmail.com", new User("Jane", "Doe", "jane@gmail.com", "04/04/1990", "Female", "jane123"));
    }

    public static void addUser(User user) {
        users.put(user.getEmail(), user);
    }

    public static Map<String, User> getUsers() {
        return users;
    }
}
