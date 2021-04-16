package view_main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import launcher.PasswordManager;
import launcher.PasswordManagerLauncher;
import model.PasswordManagerModel;
import user.InternetAccount;
import user.User;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainController {
    private PasswordManagerModel model;
    private Stage stage;
    private User user;
    private Map<String,HBox> hboxMap;

    @FXML
    private Button addNewPasswordButton;

    @FXML
    private VBox passwordsVBox;

    @FXML
    private Button logoutButton;

    /**
     * FXMl file can only call a default constructor, but we can't do much inside it, so let's just
     * remove it. For example, we want to transfer the PasswordManagerModel object from the LoginController class to
     * the MainController class. Instead, we use a method that is called by the LoginController class that will do
     * this job, along many others.
     *
     * @param model
     */
    public void initialize(PasswordManagerModel model) throws IOException {
        System.out.println("Model transferred from login window to main window");
        System.out.println("This account has these domains stored:");

        this.model = model;
        this.user = model.getCurrentUser();
        hboxMap = new HashMap<String, HBox>();
        for (String domain : user.getInternetAccounts().keySet()) {
            System.out.println(domain);
            HBox userHBox = FXMLLoader.load(getClass().getResource("UserHBoxView.fxml"));
            passwordsVBox.getChildren().add(userHBox);
            hboxMap.put(domain,userHBox);
        }
    }

    public void addNewPasswordButtonAction() {
        System.out.println("Adding new password...");

        //launch addNewPasswordWindow
    }

    public void logoutButtonAction() throws Exception {
        logoutButton.getScene().getWindow().hide();
        PasswordManagerLauncher pml = new PasswordManagerLauncher();
        pml.start(new Stage());
    }
}
