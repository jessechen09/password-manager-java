package view_main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.PasswordManagerModel;
import user.User;

import java.awt.event.ActionEvent;

public class MainController {
    private PasswordManagerModel model;
    private Stage stage;
    private User user;

    @FXML
    private Button addNewPasswordButton;

    @FXML
    private VBox passwordsVBox;

    public MainController(PasswordManagerModel model, Stage stage) {
        this.model = model;
        this.stage = stage;
        this.user = model.getCurrentUser();
        passwordsVBox.getChildren().addAll(new Label("here"));
//        user.getInternetAccounts().forEach((domain, internetAccount)->
//            passwordsVBox.getChildren().add(new Label(domain)));
    }

    public void addNewPasswordButtonAction (ActionEvent event){

    }
}
