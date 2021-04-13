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

    private static final String LOGIN_BUTTON_IDLE_STYLE = "-fx-background-color: #C92D39;";
    private static final String LOGIN_BUTTON_HOVER_STYLE = "-fx-background-color: #DA6770;";
    //private static final String LOGIN_BUTTON_PRESSED_STYLE = "-fx-background-color: #E9A5AA;";

    /**
     * Constructor.
     */
    public LoginController() {
        model = new PasswordManagerModel();
        System.out.println("New model created");
    }

    //=============== Methods ============================================

    public void loginButtonOnAction() {
        //loginButton.setStyle(LOGIN_BUTTON_PRESSED_STYLE);
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
        invalidLoginLabel.setVisible(false);
        if (event.getCode() == KeyCode.ENTER) loginButtonOnAction();
    }

    /**
     * These two methods modify the button's color as we move our mouse over it.
     */
    public void loginButtonOnEnter() { loginButton.setStyle(LOGIN_BUTTON_HOVER_STYLE); }

    public void loginButtonOnExit() { loginButton.setStyle(LOGIN_BUTTON_IDLE_STYLE); }

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
