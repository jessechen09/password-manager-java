package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import user.InternetAccount;
import javafx.scene.control.Label;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;

/**
 * Controls the user HBox.
 *
 * @author Jesse Chen / Hugo Pereira
 */

public class UserHBoxController extends HBox {

    @FXML
    private Label domainLabel;
    @FXML
    private HBox accHBox;
    @FXML
    private Button copyPasswordButton;

    private InternetAccount internetAccount;
    private List<InternetAccount> internetAccountList;
    private VBox passwordsVBox;
    private VBox originalPasswordsVBox;
    private ObservableList<Node> nodes;

    private static Comparator<InternetAccount> domainComparator = Comparator.comparing(InternetAccount::getDomain);

    public void initialize(InternetAccount internetAccount, List<InternetAccount> internetAccountsList, VBox passwordsVBox) {
        this.internetAccount = internetAccount;
        this.internetAccountList = internetAccountsList;
        this.originalPasswordsVBox = passwordsVBox;
        this.passwordsVBox = passwordsVBox;
        this.nodes = FXCollections.observableArrayList(passwordsVBox.getChildren());
    }

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

    public void openWebsite(MouseEvent mouseEvent) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(internetAccount.getDomain()));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    public void sortDomain() {
        // Sort the internetAccountList based on the domain name
        Collections.sort(internetAccountList, domainComparator);

        ObservableList<Node> nodes = FXCollections.observableArrayList(passwordsVBox.getChildren());
        nodes.sort((node1, node2) -> {
            Label label1 = (Label) ((Parent) node1).getChildrenUnmodifiable().get(0);
            Label label2 = (Label) ((Parent) node2).getChildrenUnmodifiable().get(0);
            return domainComparator.compare(getInternetAccountFromLabel(label1), getInternetAccountFromLabel(label2));
        });

        passwordsVBox.getChildren().setAll(nodes);
    }

    private InternetAccount getInternetAccountFromLabel(Label label) {
        return internetAccountList.stream()
                .filter(account -> account.getDomain().equals(label.getText()))
                .findFirst()
                .orElse(null);
    }

    public void unsortDomain() {
        VBox aux = passwordsVBox;
        passwordsVBox = originalPasswordsVBox;
        originalPasswordsVBox = aux;

        passwordsVBox.getChildren().setAll(nodes);
    }
}
