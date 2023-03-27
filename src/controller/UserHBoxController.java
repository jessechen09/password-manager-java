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

    private static Comparator<InternetAccount> domainComparator = Comparator.comparing(InternetAccount::getDomain);

    public void initialize(InternetAccount internetAccount) {
        this.internetAccount = internetAccount;
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

    public void sortDomain(List<InternetAccount> internetAccountsList, VBox passwordsVBox) {
        // Sort the internetAccountList based on the domain name
        Collections.sort(internetAccountsList, domainComparator);

        ObservableList<Node> nodes = FXCollections.observableArrayList(passwordsVBox.getChildren());
        nodes.sort((node1, node2) -> {
            Label label1 = (Label) ((Parent) node1).getChildrenUnmodifiable().get(0);
            Label label2 = (Label) ((Parent) node2).getChildrenUnmodifiable().get(0);
            return domainComparator.compare(getInternetAccountFromLabel(internetAccountsList,label1), getInternetAccountFromLabel(internetAccountsList,label2));
        });

        passwordsVBox.getChildren().setAll(nodes);
    }

    private InternetAccount getInternetAccountFromLabel(List<InternetAccount> internetAccountsList, Label label) {
        return internetAccountsList.stream()
                .filter(account -> account.getDomain().equals(label.getText()))
                .findFirst()
                .orElse(null);
    }

    public void unsortDomain(List<InternetAccount> internetAccountsList, VBox passwordsVBox) {
        List<InternetAccount> originalOrder = new ArrayList<>(internetAccountsList);
        // Sort the internetAccountList based on the domain name
        Collections.sort(internetAccountsList, domainComparator);

        ObservableList<Node> nodes = FXCollections.observableArrayList(passwordsVBox.getChildren());
        nodes.sort((node1, node2) -> {
            Label label1 = (Label) ((Parent) node1).getChildrenUnmodifiable().get(0);
            Label label2 = (Label) ((Parent) node2).getChildrenUnmodifiable().get(0);
            InternetAccount internetAccount1 = getInternetAccountFromLabel(internetAccountsList,label1);
            InternetAccount internetAccount2 = getInternetAccountFromLabel(internetAccountsList,label2);
            int index1 = originalOrder.indexOf(internetAccount1);
            int index2 = originalOrder.indexOf(internetAccount2);
            return Integer.compare(index1, index2);
        });

        passwordsVBox.getChildren().setAll(nodes);
    }
}
