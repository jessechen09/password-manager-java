import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author jessechen
 *
 */
public class PasswordManagerModel {
    private List<User> userList;
    private User user; // the current user, i.e. whoever logged in

    public PasswordManagerModel() {
        this.userList = new ArrayList<User>();
        try {
            Scanner scanner = new Scanner(new File("." + File.separator + "src" + File.separator + "users.txt"));
            while (scanner.hasNextLine()) {
                userList.add(new User(new Account(scanner.next(), scanner.next())));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean hasUser(String username) {
        for (User user : userList)
            if (user.getAccount().getUserName().equals(username))
                return true;
        return false;
    }
}
