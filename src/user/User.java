package user;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The User object holds all account info, including the account info for
 * logging into the password manager and all Internet account info.
 *
 * @author jessechen
 */
public class User {
    private Account account;
    private HashMap<String, InternetAccount> internetAccounts;

    // take account as parameter because maybe we want to add more info to account in future
    public User(Account account) {
        this.account = account;
        internetAccounts = new HashMap<String, InternetAccount>();

        // load in all of user's internet account info
        try {
            Scanner scanner =
                    new Scanner(new File("." + File.separator + "src" + File.separator + "data" + File.separator +
                            "users.txt"));
            String domain;
            String username;
            String password;
            while (scanner.hasNextLine()) {
                domain = scanner.next();
                username = scanner.next();
                password = scanner.next();
                internetAccounts.put(domain, new InternetAccount(domain, username, password));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public InternetAccount getInternetAccount(String domain) {
        return internetAccounts.get(domain);
    }

    public Account getAccount() {
        return account;
    }

}
