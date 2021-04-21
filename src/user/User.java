package user;

import model.PasswordManagerModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The User object holds all account info, including the account info for
 * logging into the password manager and all Internet account info.
 *
 * @author Jesse Chen
 */

public class User {
    private Account account;
    private HashMap<String, InternetAccount> internetAccounts;

    // take account as parameter because maybe we want to add more info to account in future
    public User(Account account) {
        this.account = account;
        this.internetAccounts = new HashMap<String, InternetAccount>();

        // load in all of user's internet account info
        try {
            Scanner scanner = new Scanner(new File(PasswordManagerModel.DATA_DIRECTORY + account.getUserName() +
                    ".txt"));
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

    /**
     * @param domain
     * @return Returns the InternetAccount corresponding to the input domain.
     */
    public InternetAccount getInternetAccount(String domain) { return internetAccounts.get(domain); }

    /**
     * @return Returns this User's Acccount.
     */
    public Account getAccount() { return account; }

    /**
     * @return Returns a HashMap of InternetAccounts.
     */
    public HashMap<String, InternetAccount> getInternetAccounts() { return internetAccounts; }

    /**
     * Adds an InternetAccount to the HashMap and writes the domain, username, and password in the corresponding .txt
     * file.
     *
     * @param internetAccount
     */
    public void addInternetAccount(InternetAccount internetAccount) {
        try {
            String domain = internetAccount.getDomain();
            String username = internetAccount.getUserName();
            String password = internetAccount.getPassword();
            File file = new File(PasswordManagerModel.DATA_DIRECTORY + account.getUserName() + ".txt");
            FileWriter writer = new FileWriter(file, true);
            writer.write("\n" + domain + " " + username + " " + password);
            writer.close();
            internetAccounts.put(domain, new InternetAccount(domain, username, password));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
