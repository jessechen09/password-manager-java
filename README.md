# password-manager-java

DISCLAIMER: This is not a true password manager and has ZERO security features. Do NOT use it to store any passwords.

### Intro from **jessechen09/password-manager-java**:
This is my first personal project, and I think it's a good starting point for those who are trying to find projects to work on. By working on this project, you also practice reading and understanding someone else's code, which may not necessarily be well written! Hopefully, mine is okay.

### Prerequisites

Java, OOP, MVC architecture, and JavaFX, to list a few.

### Task ideas from **jessechen09/password-manager-java**:

Most of them I already work one. 

| Estimated difficulty | Fixed?       | Task        |
| :------------------: | :----------: | :---------- |
|Easy                  | **YES**      |Modify the UI.|
|Easy                  | **YES**      |Change the extension of the files used to store the data (json).|
|Easy                  | **YES**      |Do not allow users in the same domain to have the different passwords / duplicate data.|
|Easy                  | **YES**      |Investigate how things are handled when we have a multiple InternetAccounts with the same domain and optimize.|
|Easy/Medium           | **YES**      |Look around and fix any bugs.|
|Medium                | **YES**      |As of now, when we press the close button (the x button the top corners), the parent pane remains disabled. Fix this by adding a "cancel" button|
|Medium                | **YES**      |Add a feature that allows users to delete domains from the password manager.|
|Medium                | **YES**      |Add a feature that allows users to delete their account from the password manager.|
|Medium                | NOT YET      |Add a feature that allows users to change their login password.|
|Medium                | NOT YET      |Add a feature that allows users to change their domains/usernames/passwords for each InternetAccount.|
|Medium                | **YES**      |Add a sort feature for the domains, i.e. A-Z and Z-A.|
|Medium                | **YES**      |Add a feature that launches the default browser when user clicks on the password domain and open the website.|
|Hard                  | **YES**      |Add animations to UI.|
|Hard                  | NOT YET      |Instead of using .txt files, use a data base to store all the info.|
|Super Hard            | NOT YET      |Implement security features.|

## How this project works

This project uses the MVC software design pattern. The FXML files are the views, which are controlled by separate controller classes. There is one main model that stores all the user and account information. The "database" is simulated with .json files, which the model reads when a user logs in or writes to when new users or internet accounts are added.
Soon the data will be saved in the cloud, in oracle.
