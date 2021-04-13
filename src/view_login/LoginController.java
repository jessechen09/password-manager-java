package view_login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import view_main.MainController;
import model.PasswordManagerModel;

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
        System.out.println("New model created");
    }

    //=============== Methods ============================================

    public void loginButtonOnAction() {
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

    /**
     * Triggers an ActionEvent on the loginButton when user presses enter.
     *
     * @param event
     */
    public void textFieldOnEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) loginButtonOnAction();
    }

    /**
     * Opens the main password manager window.
     */
    private void openMainWindow() {
        try {
            String viewPath = ".." + File.separator + "view_main" + File.separator + "MainView" + ".fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
            Stage stage = new Stage();
            Parent parent = loader.load();
            MainController mc = loader.getController();
            mc.initialize(model);
            stage.setTitle("Password Manager");
            stage.setScene(new Scene(parent));
            stage.setResizable(false);
            stage.show();
            loginButton.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
