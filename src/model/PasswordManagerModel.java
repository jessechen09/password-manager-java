package model;

import user.Account;
import user.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author jessechen
 */
public class PasswordManagerModel {
    private Map<String, User> userMap;
    private User currentUser; // the current user, i.e. whoever logged in
    private String usersDirectory;

    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final String MAIN_DIRECTORY = ".." + File.separator;
    public static final String SRC_DIRECTORY = "src" + File.separator;
    public static final String VIEW_DIRECTORY = MAIN_DIRECTORY + "view" + File.separator;
    public static final String DATA_DIRECTORY = SRC_DIRECTORY + "data" + File.separator;
    public static final String CONTROLLER_DIRECTORY = MAIN_DIRECTORY + "controller" + File.separator;

    public PasswordManagerModel() {
        userMap = new HashMap<String, User>();
        // The following does not work for some reason:
        // DATA_DIRECTORY + "users.txt"
        usersDirectory = DATA_DIRECTORY + "users.txt";
        try {
            Scanner scanner = new Scanner(new File(usersDirectory));
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

    //=============== Methods ==========================================================================================

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

    public void addUser(String username, String password) {
        try {  // add user to database, i.e. "users.txt"
            File usersFile = new File(usersDirectory);
            FileWriter writer = new FileWriter(usersFile, true);
            writer.write("\n" + username + " " + password);
            writer.close();

            // create username.txt file
            File userFile = new File(DATA_DIRECTORY + username + ".txt");
            userFile.createNewFile();

            // add user to userMap
            userMap.put(username, new User(new Account(username, password)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
