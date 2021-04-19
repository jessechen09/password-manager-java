package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import user.User;

public class AddPassController extends SmallWindow {

    @FXML
    private TextField domainTextField;

    @FXML
    private PasswordField passwordField2;

    private User user;

    public void initialize(User user, BorderPane parentBorderPane, Stage addPassStage) {
        this.user = user;
        super.parentBorderPane = parentBorderPane;
        super.currentStage = addPassStage;
    }

    @Override
    public void mainButtonOnAction() {
        String domain = domainTextField.getText();
        String username = usernameTextField.getText();
        String password1 = passwordField1.getText();
        String password2 = passwordField2.getText();

        if (!password1.equals(password2)) {
            invalidLabel.setText("Passwords do not match");
            invalidLabel.setVisible(true);
        } else {
            user.addInternetAccount(domain, username, password1);
            parentBorderPane.setDisable(false);
            currentStage.close();
        }
    }
}

