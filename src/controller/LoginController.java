package controller;

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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Colors;
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

    @FXML
    private Button registerButton;

    @FXML
    private BorderPane borderPane;

    private PasswordManagerModel model;

    public LoginController() {
        model = new PasswordManagerModel();
        System.out.println("New model created");
    }

    //=============== Login button =====================================================================================

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
    public void loginTextFieldOnEnter(KeyEvent event) {
        invalidLoginLabel.setVisible(false);
        if (event.getCode() == KeyCode.ENTER) loginButtonOnAction();
    }

    /**
     * These two methods modify the button's color as we move our mouse over it. This can also be converted to
     * lambdas if desired.
     */
    public void loginButtonOnEnter() { loginButton.setStyle(Colors.setBackgroundColor(Colors.LIGHT_RED)); }

    public void loginButtonOnExit() { loginButton.setStyle(Colors.setBackgroundColor(Colors.MAIN_RED)); }

    /**
     * Opens the main password manager window.
     */
    private void openMainWindow() {
        try {
            String viewPath = PasswordManagerModel.VIEW_DIRECTORY + "MainView.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
            Stage mainStage = new Stage();
            Parent parent = loader.load();
            MainController mainController = loader.getController();
            mainController.initialize(model);
            mainStage.setTitle("Password Manager");
            mainStage.setScene(new Scene(parent));
            mainStage.setResizable(false);
            mainStage.show();
            loginButton.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //================ Register button =================================================================================

    public void registerButtonAction() {
        System.out.println("Registering new user");
        openRegisterWindow();
    }

    public void registerButtonOnEnter() { registerButton.setStyle(Colors.setBackgroundColor(Colors.LIGHT_GREY)); }

    public void registerButtonOnExit() { registerButton.setStyle(Colors.setBackgroundColor(Colors.WHITE)); }

    // https://stackoverflow.com/questions/17014012/how-to-unmask-a-javafx-passwordfield-or-properly-mask-a-textfield

    private void openRegisterWindow() {
        try {
            String viewPath = PasswordManagerModel.VIEW_DIRECTORY + "RegisterView.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
            Stage regStage = new Stage();
            Parent parent = loader.load();
            RegisterController regController = loader.getController();
            regController.initialize(model, borderPane, regStage);
            regStage.setTitle("Register");
            regStage.setScene(new Scene(parent));
            regStage.setResizable(false);
            regStage.show();
            borderPane.setDisable(true);
            //            registerButton.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
