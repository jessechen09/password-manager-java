package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Colors;

/**
 * Abstract class for the small windows, which is basically all windows except the main window.
 *
 * @author Jesse Chen
 */

public abstract class SmallWindowController {

    @FXML
    protected Button mainButton;

    @FXML
    protected TextField usernameTextField;

    @FXML
    protected PasswordField passwordField1;

    @FXML
    protected Label invalidLabel;

    public abstract void mainButtonOnAction();

    /**
     * These two methods modify the button's color as we move our mouse over it. This can also be converted to
     * lambdas if desired.
     */
    public void mainButtonOnEnter() { mainButton.setStyle(Colors.setBackgroundColor(Colors.LIGHT_RED)); }

    public void mainButtonOnExit() { mainButton.setStyle(Colors.setBackgroundColor(Colors.MAIN_RED)); }

    /**
     * Clicks on main button when user presses return.
     *
     * @param event
     */
    public void fieldOnEnter(KeyEvent event) {
        invalidLabel.setVisible(false);
        if (event.getCode() == KeyCode.ENTER) mainButtonOnAction();
    }
}
