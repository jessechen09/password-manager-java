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
    private String dataDirectory;
    private String usersDirectory;

    public PasswordManagerModel() {
        userMap = new HashMap<String, User>();
        dataDirectory = "." + File.separator + "src" + File.separator + "data" + File.separator;
        usersDirectory = dataDirectory + "users.txt";
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

    public void addUser(String username, String password) throws IOException {
        // add user to database, i.e. "users.txt"
        System.out.println(usersDirectory);
        File usersFile = new File(usersDirectory);
        FileWriter writer = new FileWriter(usersFile, true);
        writer.write("\n" + username + " " + password);
        writer.close();

        // create username.txt file
        File userFile = new File(dataDirectory + username + ".txt");
        userFile.createNewFile();

        // add user to userMap
        userMap.put(username, new User(new Account(username, password)));
    }
}
