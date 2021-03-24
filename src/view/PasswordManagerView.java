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

    private PasswordManagerModel model = new PasswordManagerModel();
    private PasswordManagerController controller= new PasswordManagerController(model);
    private Parent parent;

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Label invalidLoginLabel;

    @Override
    public void start(Stage stage) throws Exception {
        usernameTextField = new TextField();
        passwordTextField = new PasswordField();
        loginButton = new Button();
        invalidLoginLabel = new Label();

        parent = FXMLLoader.load(getClass().getResource("view.fxml"));
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.show();
    }

    public void loginButtonOnAction(ActionEvent event){
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        if (controller.hasUser(username) && controller.isCorrectPassword(username,password)) {
            controller.setUser(controller.getUser(username));
            System.out.println("Logged in: " + controller.getCurrentUserName());
        } else {
            invalidLoginLabel.setVisible(true);
            invalidLoginLabel.setText("Invalid login. Please try again.");
        }
    }

    public void textFieldOnEnter(KeyEvent event){
        if(event.getCode()==KeyCode.ENTER)
            loginButtonOnAction(new ActionEvent());
    }

}
