package model_controller;

import user.Account;
import user.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author jessechen
 */
public class PasswordManagerModel {
    private Map<String, User> userMap;
    private User currentUser; // the current user, i.e. whoever logged in

    public PasswordManagerModel() {
        userMap = new HashMap<String, User>();
        try {
            Scanner scanner =
                    new Scanner(new File("." + File.separator + "src" + File.separator + "data" + File.separator +
                            "users.txt"));
            String username;
            String password;
            while (scanner.hasNextLine()) {
                username = scanner.next();
                password = scanner.next();
                userMap.put(username, new User(new Account(username, password)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void setUser(User user) {
        this.currentUser = user;
    }

    protected boolean hasUser(String username) {
        return userMap.containsKey(username);
    }

    protected User getUser(String username) {
        if (this.hasUser(username)) return userMap.get(username);
        return null;
    }

    protected String getCurrentUserName() {
        return currentUser.getAccount().getUserName();
    }
}
