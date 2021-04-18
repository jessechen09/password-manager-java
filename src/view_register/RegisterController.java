package view_register;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Colors;
import model.PasswordManagerModel;

import java.io.IOException;

public class RegisterController {

    private PasswordManagerModel model;
    private Stage loginStage;
    private Stage regStage;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField1;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private Label invalidInfoLabel;

    @FXML
    private Button finishButton;

    public void initialize(PasswordManagerModel model, Stage loginStage, Stage regStage) {
        this.model = model;
        this.loginStage = loginStage;
        this.regStage = regStage;
    }

    public void finishButtonOnAction() throws IOException {
        String username = usernameTextField.getText();
        String password1 = passwordField1.getText();
        String password2 = passwordField2.getText();

        // check if username is new
        if (model.hasUser(username)) {
            invalidInfoLabel.setText("User exists");
            invalidInfoLabel.setVisible(true);
        }

        // check if two passwords match
        if (!password1.equals(password2)) {
            invalidInfoLabel.setText("Passwords do not match");
            invalidInfoLabel.setVisible(true);
        }

        // create new user
        model.addUser(username, password1);
        loginStage.show();
        regStage.hide();
    }

    public void registerTextFieldOnEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) finishButtonOnAction();
    }

    /**
     * These two methods modify the button's color as we move our mouse over it. This can also be converted to
     * lambdas if desired.
     */
    public void finishButtonOnEnter() { finishButton.setStyle(Colors.setBackgroundColor(Colors.LIGHT_RED)); }

    public void finishButtonOnExit() { finishButton.setStyle(Colors.setBackgroundColor(Colors.MAIN_RED)); }

}
