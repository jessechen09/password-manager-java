package view_main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.PasswordManagerModel;
import user.InternetAccount;
import user.User;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class MainController {
    private PasswordManagerModel model;
    private Stage stage;
    private User user;

    @FXML
    private Button addNewPasswordButton;

    @FXML
    private VBox passwordsVBox;

    /**
     * FXMl file can only call a default constructor, but we can't do much inside it, so let's just
     * remove it. For example, we want to transfer the PasswordManagerModel object from the LoginController class to
     * the MainController class. Instead, we use a method that is called by the LoginController class that will do
     * this job, along many others.
     *
     * @param model
     */
    public void initialize(PasswordManagerModel model) {
        this.model = model;
        this.user = model.getCurrentUser();
        System.out.println("Model transferred from login window to main window");
        System.out.println("This account has these domains stored:");
        HashMap<String, InternetAccount> internetAccounts = user.getInternetAccounts();
        for (String domain : internetAccounts.keySet()) {
            System.out.println(domain);
            //display passwords in mainWindow
        }
    }

    public void addNewPasswordButtonAction() {
        System.out.println("Adding new password...");
        //launch addNewPasswordWindow
    }
}
