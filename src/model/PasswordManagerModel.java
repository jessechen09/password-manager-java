package model;

import user.Account;
import user.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * This class is the model and stores all the information by reading the .txt files. This class also accepts new users.
 *
 * @author Jesse Chen
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

    /**
     * Sets the current user when you login.
     *
     * @param user
     */
    public void setUser(User user) {
        this.currentUser = user;
    }

    /**
     * @return Returns the current user, i.e. whoever is logged in.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * @return Returns the current user's username.
     */
    public String getCurrentUserName() { return currentUser.getAccount().getUserName(); }

    /**
     * @param username
     * @return Returns the User that corresponds the input username.
     */
    public User getUser(String username) {
        return userMap.get(username);
    }

    /**
     * @param username
     * @return Returns true if there is a User with the input username in the database, false otherwise.
     */
    public boolean hasUser(String username) {
        return userMap.containsKey(username);
    }

    /**
     * @param username
     * @param enteredPassword
     * @return Returns true if the input password matches the database password of the input username, false otherwise.
     */
    public boolean isCorrectPassword(String username, String enteredPassword) {
        if (!hasUser(username)) return false;
        return getUser(username).getAccount().getPassword().equals(enteredPassword);
    }

    /**
     * Adds new user to the map of users
     *
     * @param username
     * @param password
     */
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
