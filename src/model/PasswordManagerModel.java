package model;

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

    //=============== Methods ============================================

    public void setUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public String getCurrentUserName() { return currentUser.getAccount().getUserName(); }

    public User getUser(String username) {
        return userMap.get(username);
    }

    public boolean hasUser(String username) {
        return userMap.containsKey(username);
    }

    public boolean isCorrectPassword(String username, String enteredPassword) {
        if (!hasUser(username)) return false;
        return getUser(username).getAccount().getPassword().equals(enteredPassword);
    }
}
