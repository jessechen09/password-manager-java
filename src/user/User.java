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
 * @author jessechen
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

    public InternetAccount getInternetAccount(String domain) { return internetAccounts.get(domain); }

    public Account getAccount() { return account; }

    public HashMap<String, InternetAccount> getInternetAccounts() { return internetAccounts; }
    public void addInternetAccount(String domain, String username, String password) throws IOException {
        File file = new File(PasswordManagerModel.DATA_DIRECTORY + account.getUserName()+".txt");
        FileWriter writer = new FileWriter(file,true);
        writer.write("\n"+domain+" "+username+" "+password);
        writer.close();
        internetAccounts.put(domain,new InternetAccount(domain,username,password));
    }
}
