package launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view_login.LoginController;
import view_main.MainController;

import java.io.File;

/**
 * This class launches the login page and essentially starts the password manager app.
 *
 * @author jessechen
 */
public class PasswordManagerLauncher extends Application {
    @Override
    public void start(Stage loginStage) throws Exception {
        String loginDirectory = ".." + File.separator + "view_login" + File.separator + "LoginView.fxml";
        FXMLLoader loader = new FXMLLoader((getClass().getResource(loginDirectory)));
        Parent parent = loader.load();
        LoginController loginController = loader.getController();
        loginStage.setScene(new Scene(parent));
        loginStage.setResizable(false);
        loginStage.show();
    }
}
