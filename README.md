# password-manager-java

DISCLAIMER: This is not a true password manager and has ZERO security features. Do NOT use it to store any passwords.

### Intro from **jessechen09/password-manager-java**:
This is my first personal project, and I think it's a good starting point for those who are trying to find projects to work on. By working on this project, you also practice reading and understanding someone else's code, which may not necessarily be well written! Hopefully, mine is okay.

### Prerequisites

Java, OOP, MVC architecture, and JavaFX, to list a few.

### Task ideas from **jessechen09/password-manager-java**:

| Estimated difficulty | Fixed?       | Task        |
| :------------------: | :----------: | :---------- |
|Easy                  | **YES**      |Modify the UI.|
|Easy                  | **YES**      |Add a secure icon when the domain is https.|
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

## Windows

### Login window
When you launch the application, you will first see the login window. You can type your username and password to login, or register a new user.
<p align="center">
<img src="https://github.com/hugogspereira/password-manager-java/blob/master/screenshots/mychanges/login.jpg?raw=true" width=50%>
</p>

### Register window
When you register a new user with a username that already exists, you will get an error. Be sure to choose a unique username! You will also have to type the password twice identically.
<p align="center">
<img src="https://github.com/hugogspereira/password-manager-java/blob/master/screenshots/mychanges/register.jpg?raw=true" width=50%>
</p>

### Main window
After logging in, you will reach the main window where all the passwords are stored. You can click on the buttons to copy the username and passwords, or even delete that entry.
<p align="center">
<img src="https://github.com/hugogspereira/password-manager-java/blob/master/screenshots/mychanges/main.jpg?raw=true" width=80%>
</p>

### Sort / Unsort Command
If the user is saving lots of passwords, it can be hard to search for a specific one. As such, if clicked on the "Sort Domain" button a small animation will appear and the domains will be ordered alphabetically.
<p align="center">
<img src="https://github.com/hugogspereira/password-manager-java/blob/master/screenshots/mychanges/sortDomain.jpg?raw=true" width=80%>
</p>

Then when unsorted they will return to their original order:
<p align="center">
<img src="https://github.com/hugogspereira/password-manager-java/blob/master/screenshots/mychanges/main.jpg?raw=true" width=80%>
</p>

### Secure Icon and Redirect to Website
If the user clicks on the domain, a new window will open with the website of that domain. Also, if the website is secure, a small icon will appear next to the domain.

<p align="center">
<img src="https://github.com/hugogspereira/password-manager-java/blob/master/screenshots/mychanges/addSecureSymbol.jpg?raw=true" width=80%>
</p>

### Add new password window
When you want to add a new password, the main window will be disabled until you finish adding a new password.
<p align="center">
<img src="https://github.com/hugogspereira/password-manager-java/blob/master/screenshots/mychanges/addPassMain.jpg?raw=true" width=80%>
</p>

Make sure your passwords match!
<p align="center">
<img src="https://github.com/hugogspereira/password-manager-java/blob/master/screenshots/mychanges/addPassFail.jpg?raw=true" width=50%>
</p>

### Add generated password window
The passwords can also be generated automatically by choosing the option to "Generate Password", which includes some options:
<p align="center">
<img src="https://github.com/hugogspereira/password-manager-java/blob/master/screenshots/mychanges/addPassGenerated.jpg?raw=true" width=80%>
</p>

### Delete account window
If the user intends to delete is account:
<p align="center">
<img src="https://github.com/hugogspereira/password-manager-java/blob/master/screenshots/mychanges/deleteAccount.jpg?raw=true" width=80%>
</p>

## The End
