package user;

import model.PasswordManagerModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class User {
    private Account account;
    private HashMap<String, Map<String,InternetAccount>> internetAccounts;

    public User(Account account) {
        this.account = account;
        this.internetAccounts = new HashMap<String, Map<String,InternetAccount>>();

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

                    JSONArray accountArray = jsonObject.getJSONArray("accounts");
                    for (int j = 0; j < accountArray.length(); j++) {
                        JSONObject jsonObject2 = accountArray.getJSONObject(j);
                        String username = jsonObject2.getString("username");
                        String password = jsonObject2.getString("password");
                        if(internetAccounts.containsKey(domain)) {
                            internetAccounts.get(domain).put(username, new InternetAccount(domain, username, password));
                        }
                        else {
                            internetAccounts.put(domain, new HashMap<>());
                            Map<String,InternetAccount> map = internetAccounts.get(domain);
                            map.put(username,(new InternetAccount(domain, username, password)));
                            internetAccounts.put(domain,map);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<InternetAccount> getInternetAccount(String domain) {
        return new ArrayList<>(internetAccounts.get(domain).values());
    }

    public Account getAccount() {
        return account;
    }

    public HashMap<String, Map<String,InternetAccount>> getInternetAccounts() {
        return internetAccounts;
    }

    public void addInternetAccount(InternetAccount internetAccount) {
        try {
            String domain = internetAccount.getDomain();
            String username = internetAccount.getUserName();
            String password = internetAccount.getPassword();

            // Check if there is already an existing JSON object for the domain
            File file = new File(PasswordManagerModel.DATA_DIRECTORY + account.getUserName() + ".json");
            JSONObject domainObj = null;
            JSONArray jsonArray;
            if (file.exists() && file.length() > 0) {
                Scanner scanner = new Scanner(file);
                String jsonStr = scanner.useDelimiter("\\Z").next();
                jsonArray = new JSONArray(jsonStr);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    if (obj.getString("domain").equals(domain)) {
                        domainObj = obj;
                        break;
                    }
                }
            } else {
                jsonArray = new JSONArray();
            }

            // Add the new account information to the existing JSON object or create a new one
            if (domainObj == null) {
                domainObj = new JSONObject();
                domainObj.put("domain", domain);
                JSONArray accountArray = new JSONArray();
                JSONObject accountObj = new JSONObject();
                accountObj.put("username", username);
                accountObj.put("password", password);
                accountArray.put(accountObj);
                domainObj.put("accounts", accountArray);
                jsonArray.put(domainObj);
            } else {
                JSONArray accountArray = domainObj.getJSONArray("accounts");
                JSONObject accountObj = new JSONObject();
                accountObj.put("username", username);
                accountObj.put("password", password);
                accountArray.put(accountObj);
                String newJsonStr = jsonArray.toString().replace(domainObj.toString(), domainObj.put("accounts", accountArray).toString());
                jsonArray = new JSONArray(newJsonStr);
            }

            // Write updated array back to file
            FileWriter writer = new FileWriter(file);
            writer.write(jsonArray.toString());
            writer.close();

            if(internetAccounts.containsKey(domain)) {
                internetAccounts.get(domain).put(username, new InternetAccount(domain, username, password));
            }
            else {
                internetAccounts.put(domain, new HashMap<>());
                Map<String,InternetAccount> map = internetAccounts.get(domain);
                map.put(username,(new InternetAccount(domain, username, password)));
                internetAccounts.put(domain,map);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public void removeInternetAccount(InternetAccount internetAccount) {
        String domain = internetAccount.getDomain();
        String username = internetAccount.getUserName();

        // Check if the domain exists in the user's internetAccounts
        if (internetAccounts.containsKey(domain)) {
            Map<String, InternetAccount> accounts = internetAccounts.get(domain);

            // Check if the account with the given username exists in the domain's accounts
            if (accounts.containsKey(username)) {
                accounts.remove(username);

                // Update the user's internetAccounts and the JSON file
                internetAccounts.put(domain, accounts);
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
                            if (jsonObject.getString("domain").equals(domain)) {
                                JSONArray accountArray = jsonObject.getJSONArray("accounts");
                                for (int j = 0; j < accountArray.length(); j++) {
                                    JSONObject jsonObject2 = accountArray.getJSONObject(j);
                                    if (jsonObject2.getString("username").equals(username)) {
                                        accountArray = removeJsonObjectFromArray(accountArray,j);
                                        j--;
                                        break;
                                    }
                                }

                                jsonObject.put("accounts", accountArray);
                                String newJsonStr = jsonArray.toString().replace(jsonObject.toString(), jsonObject.toString());
                                FileWriter writer = new FileWriter(file);
                                writer.write(newJsonStr);
                                writer.close();
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private JSONArray removeJsonObjectFromArray(JSONArray jsonArray, int index) throws JSONException {
        JSONArray list = new JSONArray();
        for (int i = 0; i < jsonArray.length(); i++) {
            if (i != index) {
                list.put(jsonArray.getJSONObject(i));
            }
        }
        return list;
    }

    public boolean hasAccount(String domain, String username) {
        return (internetAccounts.containsKey(domain) && internetAccounts.get(domain).containsKey(username));
    }
}