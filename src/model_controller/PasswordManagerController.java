package model_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import user.User;

/**
 * @author jessechen
 */
public class PasswordManagerController {

    private PasswordManagerModel model;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Label invalidLoginLabel;

    public PasswordManagerController() {
        model = new PasswordManagerModel();
    }

    //=============== Methods ============================================

    public void loginButtonOnAction(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        if (model.hasUser(username) && model.isCorrectPassword(username, password)) {
            model.setUser(model.getUser(username));
            System.out.println("Logged in: " + model.getCurrentUserName());
        } else {
            invalidLoginLabel.setVisible(true);
            invalidLoginLabel.setText("Invalid login. Please try again.");
        }
    }

    public void textFieldOnEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            loginButtonOnAction(new ActionEvent());
    }

}
