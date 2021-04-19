package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.PasswordManagerModel;

public class RegisterController extends SmallWindow {

    @FXML
    private PasswordField passwordField2;

    private PasswordManagerModel model;

    public void initialize(PasswordManagerModel model, BorderPane parentBorderPane, Stage regStage) {
        this.model = model;
        this.parentBorderPane = parentBorderPane;
        super.currentStage = regStage;
    }

    @Override
    public void mainButtonOnAction() {
        String username = usernameTextField.getText();
        String password1 = passwordField1.getText();
        String password2 = passwordField2.getText();

        if (model.hasUser(username)) {
            invalidLabel.setText("User exists");
            invalidLabel.setVisible(true);
        } else if (!password1.equals(password2)) {
            invalidLabel.setText("Passwords do not match");
            invalidLabel.setVisible(true);
        } else if (password1.length() < PasswordManagerModel.MIN_PASSWORD_LENGTH) {
            invalidLabel.setText("Min password length: " + PasswordManagerModel.MIN_PASSWORD_LENGTH);
            invalidLabel.setVisible(true);
        } else {
            model.addUser(username, password1);
            parentBorderPane.setDisable(false);
            currentStage.close();
        }
    }
}
