package user;

import model.PasswordManagerModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class User {
    private Account account;
    private HashMap<String, InternetAccount> internetAccounts;

    public User(Account account) {
        this.account = account;
        this.internetAccounts = new HashMap<String, InternetAccount>();

        // load in all of user's internet account info
        try {
            File file = new File(PasswordManagerModel.DATA_DIRECTORY + account.getUserName() + ".json");
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                StringBuilder jsonStr = new StringBuilder();
                while (scanner.hasNextLine()) {
                    jsonStr.append(scanner.nextLine());
                }
                JSONArray jsonArray = new JSONArray(jsonStr.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String domain = jsonObject.getString("domain");
                    String username = jsonObject.getString("username");
                    String password = jsonObject.getString("password");
                    internetAccounts.put(domain, new InternetAccount(domain, username, password));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public InternetAccount getInternetAccount(String domain) {
        return internetAccounts.get(domain);
    }

    public Account getAccount() {
        return account;
    }

    public HashMap<String, InternetAccount> getInternetAccounts() {
        return internetAccounts;
    }

    public void addInternetAccount(InternetAccount internetAccount) {
        try {
            String domain = internetAccount.getDomain();
            String username = internetAccount.getUserName();
            String password = internetAccount.getPassword();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("domain", domain);
            jsonObject.put("username", username);
            jsonObject.put("password", password);

            File file = new File(PasswordManagerModel.DATA_DIRECTORY + account.getUserName() + ".json");

            // Read in existing JSON array from file
            JSONArray jsonArray;
            if (file.exists() && file.length() > 0) {
                Scanner scanner = new Scanner(file);
                String jsonStr = scanner.useDelimiter("\\Z").next();
                jsonArray = new JSONArray(jsonStr);
            } else {
                jsonArray = new JSONArray();
            }

            // Add new object to array
            jsonArray.put(jsonObject);

            // Write updated array back to file
            FileWriter writer = new FileWriter(file);
            writer.write(jsonArray.toString());
            writer.close();

            internetAccounts.put(domain, new InternetAccount(domain, username, password));
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}