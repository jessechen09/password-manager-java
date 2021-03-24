package user;

/**
 * This is an insecure implementation of the Account object and is up to the
 * instructors to choose the modifications that the students should make.
 * 
 * @author jessechen
 *
 */

public class Account {

    private String username;
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUserName() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void changePassword(String password) {
        this.password = password;
    }
}
