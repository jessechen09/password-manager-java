package controller;
/**
 * Controls the view that allows adding a new password.
 *
 * @author Hugo Pereira
 */

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import pwd.PasswordGenerator;
import user.InternetAccount;

public class GeneratePassController extends SmallWindowController {

    @FXML
    private TextField domainTextField;

    @FXML
    private CheckBox checkBoxNumbers;

    @FXML
    private CheckBox checkBoxSymbols;

    @FXML
    private CheckBox checkBoxUpperLower;


    private MainController parentController;

    /**
     * Replicates a constructor because FXML only allows default constructors.
     *
     * @param parentController
     */
    public void initialize(MainController parentController) {
        this.parentController = parentController;
    }


    /**
     * Adds new InternetAccount to current user and displays new password HBox in main window when the finish button
     * is pressed.
     */
    @Override
    public void mainButtonOnAction() {
        String domain = domainTextField.getText();
        String username = usernameTextField.getText();
        boolean hasNumbers = checkBoxNumbers.isSelected();
        boolean hasSymbols = checkBoxSymbols.isSelected();
        boolean hasUpperAndLower = checkBoxUpperLower.isSelected();


        if(domain.isEmpty() || username.isEmpty()) {
            invalidLabel.setText("Empty field(s)");
            invalidLabel.setVisible(true);
        }
        else if(parentController.user.hasAccount(domain,username)) {
            invalidLabel.setText("This account is already being stored");
            invalidLabel.setVisible(true);
        }
        else {
            String pwd = PasswordGenerator.generatePassword(hasNumbers,hasSymbols,hasUpperAndLower);
            while(!isValidPassword(pwd)) {
                pwd = PasswordGenerator.generatePassword(hasNumbers,hasSymbols,hasUpperAndLower);
            }
            InternetAccount newInternetAccount = new InternetAccount(domain, username, pwd);
            parentController.user.addInternetAccount(newInternetAccount);
            parentController.borderPane.setDisable(false);
            parentController.addPasswordHBox(newInternetAccount);
            parentController.addPassStage.close();
        }
    }
}

