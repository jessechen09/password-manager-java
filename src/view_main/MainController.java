package view_main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.PasswordManagerModel;
import user.User;

import java.awt.event.ActionEvent;

public class MainController {
    private PasswordManagerModel model;
    private Stage stage;
    private User user;

//    @FXML
//    private Button addNewPasswordButton;

    @FXML
    private VBox passwordsVBox;

    // FXMl file can only call a default constructor
    public MainController() {
    }

    @FXML
    public void initialize() {
        System.out.println("pwbbox: " + passwordsVBox);
    }
    public void setModel(PasswordManagerModel model){
        this.model = model;
    }
//    public void addNewPasswordButtonAction(ActionEvent event) {
//        System.out.println("Adding new password...");
//    }
}
