# password-manager-java

DISCLAIMER: This is not a true password manager and has ZERO security features. Do NOT use it to store any passwords.

## Intro

This is my first personal project, and I think it's a good starting point for those who are trying to find projects to work on. By working on this project, you also practice reading and understanding someone else's code, which may not necessarily be well written! Hopefully, mine is okay.

## Prerequisites

Java, OOP, MVC architecture, and JavaFX, to list a few.

## Task ideas

Some tasks you can work on include, but are not limited to:

| Estimated difficulty | Task |
| :------------------- | :--- |
|Easy                  |Modify the UI.|
|Easy                  |Investigate how things are handled when we have a multiple InternetAccounts with the same domain and optimize.|
|Easy/Medium           |Look around and fix any bugs.|
|Medium                |As of now, when we press the close button (the x button the top corners), the parent pane remains disabled. Fix this by adding a "cancel" button|
|Medium                |Add a feature that allows users to delete domains from the password manager.|
|Medium                |Add a feature that allows users to delete their account from the password manager.|
|Medium                |Add a feature that allows users to change their login password.|
|Medium                |Add a feature that allows users to change their domains/usernames/passwords for each InternetAccount.|
|Medium                |Add a sort feature for the domains, i.e. A-Z and Z-A.|
|Medium                |Add a feature that launches the default browser when user clicks on the password domain and open the website.|
|Hard                  |Add animations to UI.|
|Hard                  |Instead of using .txt files, use a data base to store all the info.|
|Super Hard            |Implement security features.|

## How this project works

This project uses the MVC software design pattern. The FXML files are the views, which are controlled by separate controller classes. There is one main model that stores all the user and account information. The "database" is simulated with .txt files, which the model reads when a user logs in or writes to when new users or internet accounts are added.

## Windows

### Login window
When you launch the application, you will first see the login window. You can type your username and password to login, or register a new user.
<p align="center">
<img src="https://github.com/jessechen09/password-manager-java/blob/master/screenshots/login.png?raw=true" width=50%>
</p>

### Register window
When you register a new user with a username that already exists, you will get an error. Be sure to choose a unique username! You will also have to type the password twice indentically.
<p align="center">
<img src="https://github.com/jessechen09/password-manager-java/blob/master/screenshots/registerFail.png?raw=true" width=45%>
</p>

### Main window
After logging in, you will reach the main window where all the passwords are stored. You can click on the buttons to copy the username and passwords. Lots of work can be done here, so be creative!
<p align="center">
<img src="https://github.com/jessechen09/password-manager-java/blob/master/screenshots/main.png?raw=true" width=88%>
</p>

### Add new password window
When you want to add a new password, the main window will be disabled until you finish adding a new password.
<p align="center">
<img src="https://github.com/jessechen09/password-manager-java/blob/master/screenshots/addPassMain.png?raw=true" width=74%>
</p>

Make sure your passwords match!
<p align="center">
<img src="https://github.com/jessechen09/password-manager-java/blob/master/screenshots/addPassFail.png?raw=true" width=50%>
</p>

Everything is fine now.
<p align="center">
<img src="https://github.com/jessechen09/password-manager-java/blob/master/screenshots/addPass.png?raw=true" width=50%>
</p>

## The End
