package model_controller;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * @author jessechen
 */
public class LoginController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Label invalidLoginLabel;

    private PasswordManagerModel model;

    public LoginController() {
        model = new PasswordManagerModel();
    }

    //=============== Methods ============================================

    public void loginButtonOnAction(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        if (model.hasUser(username) && model.isCorrectPassword(username, password)) {
            model.setUser(model.getUser(username));
            System.out.println("Logged in: " + model.getCurrentUserName());
            openMainWindow();
        } else {
            invalidLoginLabel.setVisible(true);
            invalidLoginLabel.setText("Invalid login. Please try again.");
        }
    }

    public void textFieldOnEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) loginButtonOnAction(new ActionEvent());
    }

    private void openMainWindow() {
        Parent parent;
        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource(".." + File.separator + "view" + File.separator + "MainView" +
                            ".fxml"));
            loader.setController(new MainController(model));
            parent = loader.load();
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(parent));
            stage.show();
            loginButton.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
