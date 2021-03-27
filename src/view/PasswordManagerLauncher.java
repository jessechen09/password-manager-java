package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class launches the login page and essentially starts the password manager app.
 * @author jessechen
 */
public class PasswordManagerLauncher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("LoginView.fxml"))));
        stage.setResizable(false);
        stage.show();
    }
}
