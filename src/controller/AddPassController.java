package controller;
/**
 * Controls the view that allows adding a new password.
 *
 * @author Jesse Chen
 */

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import user.InternetAccount;

public class AddPassController extends SmallWindowController {

    @FXML
    private TextField domainTextField;

    @FXML
    private PasswordField passwordField2;

    private MainController parentController;

    /**
     * Replicates a constructor because FXML only allows default constructors.
     *
     * @param parentController
     */
    public void initialize(MainController parentController) {
        this.parentController = parentController;
    }


    /**
     * Adds new InternetAccount to current user and displays new password HBox in main window when the finish button
     * is pressed.
     */
    @Override
    public void mainButtonOnAction() {
        String domain = domainTextField.getText();
        String username = usernameTextField.getText();
        String password1 = passwordField1.getText();
        String password2 = passwordField2.getText();

        if(domain.isEmpty() || username.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
            invalidLabel.setText("Empty field(s)");
            invalidLabel.setVisible(true);
        }
        else if (!password1.equals(password2)) {
            invalidLabel.setText("Passwords do not match");
            invalidLabel.setVisible(true);
        }
        else if(isValidPassword(password1)) {
            invalidLabel.setText("Passwords is invalid (e.g. Spaces / Non-ASCII characters / Control characters / Certain special characters)");
            invalidLabel.setVisible(true);
        }
        else {
            InternetAccount newInternetAccount = new InternetAccount(domain, username, password1);
            parentController.user.addInternetAccount(newInternetAccount);
            parentController.borderPane.setDisable(false);
            parentController.addPasswordHBox(newInternetAccount);
            parentController.addPassStage.close();
        }
    }
}

