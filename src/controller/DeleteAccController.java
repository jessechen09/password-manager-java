package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controls to delete account window.
 *
 * @author Hugo Pereira
 */

public class DeleteAccController extends SmallWindowController {

    private MainController parentController;

    @FXML
    protected Button yesButton;


    public void initialize(MainController parentController) {
        this.parentController = parentController;
    }


    public void yesButtonOnAction() throws Exception {
        parentController.removeUser();
        parentController.addPassStage.close();
        parentController.logoutButtonAction();
    }

    @Override
    public void mainButtonOnAction() {
        try {
            parentController.openMainViewControlerOnClosing();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
