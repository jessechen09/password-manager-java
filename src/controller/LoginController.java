package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Colors;
import model.PasswordManagerModel;

import java.io.IOException;

/**
 * Controls the login window.
 *
 * @author Jesse Chen
 */

public class LoginController extends SmallWindowController {


    @FXML
    private Button registerButton;

    @FXML
    BorderPane borderPane;

    PasswordManagerModel model;
    Stage regStage;

    /**
     * Default constructor.
     */
    public LoginController() {
        model = new PasswordManagerModel();
        System.out.println("New model created");
    }

    //=============== Login button =====================================================================================


    /**
     * Opens the main password manager window and adds user info to the model.
     */
    @Override
    public void mainButtonOnAction() {
        String username = usernameTextField.getText();
        String password = passwordField1.getText();
        if (model.hasUser(username) && model.isCorrectPassword(username, password)) {
            model.setUser(model.getUser(username));
            System.out.println("Logged in: " + model.getCurrentUserName());
            openMainWindow();
        } else {
            invalidLabel.setVisible(true);
            invalidLabel.setText("Invalid login. Please try again.");
        }
    }

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
            mainButton.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //================ Register button =================================================================================

    /**
     * Opens register window.
     */
    public void registerButtonAction() {
        System.out.println("Registering new user");
        try {
            String viewPath = PasswordManagerModel.VIEW_DIRECTORY + "RegisterView.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
            regStage = new Stage();
            Parent parent = loader.load();
            RegisterController regController = loader.getController();
            regController.initialize(this);
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

    /**
     * Allows color changes when mouse hovers over button.
     */
    public void registerButtonOnEnter() { registerButton.setStyle(Colors.setBackgroundColor(Colors.LIGHT_GREY)); }

    public void registerButtonOnExit() { registerButton.setStyle(Colors.setBackgroundColor(Colors.WHITE)); }

    // https://stackoverflow.com/questions/17014012/how-to-unmask-a-javafx-passwordfield-or-properly-mask-a-textfield
}
