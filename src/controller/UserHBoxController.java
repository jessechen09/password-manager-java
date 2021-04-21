package controller;

import javafx.scene.layout.HBox;
import user.InternetAccount;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * Controls the user HBox.
 *
 * @author Jesse Chen
 */

public class UserHBoxController extends HBox {

    private InternetAccount internetAccount;

    public void initialize(InternetAccount internetAccount) { this.internetAccount = internetAccount; }

    /**
     * Copy the username to the clipboard.
     */
    public void copyUsernameButtonOnAction() { copy(internetAccount.getUserName()); }

    /**
     * Copy the user password to the clipboard.
     */
    public void copyPasswordButtonOnAction() { copy(internetAccount.getPassword()); }

    /**
     * Copy the string to the clipboard.
     *
     * @param string
     */
    private void copy(String string) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(string), null);
    }


    /**
     * Make button color lighter when mouse hovers over button.
     */
    public void buttonOnEnter() {
        // TODO: implement this method!
    }

    /**
     * Make button color normal when mouse leaves button.
     */
    public void buttonOnExit() {
        // TODO: implement this method!
    }

}
