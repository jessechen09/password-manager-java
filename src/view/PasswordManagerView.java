package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;
import model_controller.PasswordManagerController;
import model_controller.PasswordManagerModel;


/**
 * 
 * @author jessechen
 *
 */
public class PasswordManagerView extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("view.fxml"));
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.show();
    }
}
