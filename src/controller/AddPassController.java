package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Colors;
import user.User;

import java.io.IOException;

public class AddPassController {

    @FXML
    private TextField domainTextField;

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

    private User user;
    private BorderPane parentBorderPane;
    private Stage addPassStage;

    public void initialize(User user, BorderPane parentBorderPane, Stage addPassStage) {
        this.user = user;
        this.parentBorderPane = parentBorderPane;
        this.addPassStage = addPassStage;
    }

    public void finishButtonOnAction() throws IOException {
        String domain = domainTextField.getText();
        String username = usernameTextField.getText();
        String password1 = passwordField1.getText();
        String password2 = passwordField2.getText();

        if (!password1.equals(password2)) {
            invalidInfoLabel.setText("Passwords do not match");
            invalidInfoLabel.setVisible(true);
        } else {
            user.addInternetAccount(domain, username, password1);
            parentBorderPane.setDisable(false);
            addPassStage.close();
        }
    }

    public void textFieldOnEnter(KeyEvent event) throws IOException {
        invalidInfoLabel.setVisible(false);
        if (event.getCode() == KeyCode.ENTER) finishButtonOnAction();
    }

    /**
     * These two methods modify the button's color as we move our mouse over it. This can also be converted to
     * lambdas if desired.
     */
    public void finishButtonOnEnter() { finishButton.setStyle(Colors.setBackgroundColor(Colors.LIGHT_RED)); }

    public void finishButtonOnExit() { finishButton.setStyle(Colors.setBackgroundColor(Colors.MAIN_RED)); }
}
