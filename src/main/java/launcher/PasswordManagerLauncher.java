package launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.LoginController;
import model.PasswordManagerModel;

import java.io.File;

/**
 * This class launches the login page and essentially starts the password manager app.
 *
 * @author Jesse Chen
 */

public class PasswordManagerLauncher extends Application {

    @Override
    public void start(Stage loginStage) throws Exception {
        String loginDirectory = PasswordManagerModel.VIEW_DIRECTORY + "LoginView.fxml";
        FXMLLoader loader = new FXMLLoader((getClass().getResource(loginDirectory)));
        Parent parent = loader.load();
        loginStage.setScene(new Scene(parent));
        loginStage.setResizable(false);
        loginStage.show();
    }
}
