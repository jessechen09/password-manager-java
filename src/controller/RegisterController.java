package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import model.PasswordManagerModel;

/**
 * Controls the register window.
 *
 * @author Jesse Chen
 */

public class RegisterController extends SmallWindowController {

    @FXML
    private PasswordField passwordField2;

    private LoginController loginController;

    public void initialize(LoginController loginController) {
        this.loginController = loginController;
    }


    /**
     * Adds new user to the current model when the finish button is pressed.
     */
    @Override
    public void mainButtonOnAction() {
        String username = usernameTextField.getText();
        String password1 = passwordField1.getText();
        String password2 = passwordField2.getText();

        if (loginController.model.hasUser(username)) {
            invalidLabel.setText("User exists");
            invalidLabel.setVisible(true);
        } else if (!password1.equals(password2)) {
            invalidLabel.setText("Passwords do not match");
            invalidLabel.setVisible(true);
        } else if (password1.length() < PasswordManagerModel.MIN_PASSWORD_LENGTH) {
            invalidLabel.setText("Min password length: " + PasswordManagerModel.MIN_PASSWORD_LENGTH);
            invalidLabel.setVisible(true);
        } else {
            loginController.model.addUser(username, password1);
            loginController.borderPane.setDisable(false);
            loginController.regStage.close();
        }
    }
}
