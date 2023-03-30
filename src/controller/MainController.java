package controller;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import launcher.PasswordManagerLauncher;
import model.PasswordManagerModel;
import user.InternetAccount;
import user.User;
import java.io.IOException;

/**
 * Controls the main password manager window.
 *
 * @author Jesse Chen / Hugo Pereira
 */

public class MainController {

    @FXML
    Button addNewPasswordButton;

    @FXML
    Button addGeneratedPasswordButton;

    @FXML
    VBox passwordsVBox;

    @FXML
    private Button logoutButton;

    @FXML
    private Button reorderButton;

    @FXML
    BorderPane borderPane;

    User user;
    Stage addPassStage;

    private int clickCount;
    private PasswordManagerModel model;
    private SequentialTransition fadeTransition;
    private SequentialTransition unfadeTransition;


    /**
     * FXMl file can only call a default constructor, but we can't do much inside it, so let's just
     * remove it. For example, we want to transfer the PasswordManagerModel object from the LoginController class to
     * the MainController class. Instead, we use a method that is called by the LoginController class that will do
     * this job, along many others.
     *
     * @param model
     */
    public void initialize(PasswordManagerModel model) {
        System.out.println("Model transferred from login window to main window");
        System.out.println("This account has these domains stored:");

        this.model = model;
        this.clickCount = 1;
        this.user = model.getCurrentUser();
        for (String domain : user.getInternetAccounts().keySet()) {
            for (InternetAccount internetAccount : user.getInternetAccount(domain)) {
                addPasswordHBox(internetAccount);
            }
        }

        FadeTransition fade = new FadeTransition(Duration.seconds(1), reorderButton);
        fade.setFromValue(1.0);
        fade.setToValue(0.8);
        fade.setAutoReverse(false);
        fadeTransition = new SequentialTransition(fade);

        FadeTransition unfade = new FadeTransition(Duration.seconds(1), reorderButton);
        unfade.setFromValue(0.65);
        unfade.setToValue(1.0);
        unfade.setAutoReverse(false);
        unfadeTransition = new SequentialTransition(unfade);
    }

    /**
     * Opens the window for adding new passwords.
     */
    public void addNewPasswordButtonAction() {
        System.out.println("Adding new password...");
        try {
            String viewPath = PasswordManagerModel.VIEW_DIRECTORY + "AddPassView.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
            addPassStage = new Stage();
            Parent parent = loader.load();
            AddPassController addPassController = loader.getController();
            addPassController.initialize(this);
            addPassStage.setTitle("Add Password");
            addPassStage.setScene(new Scene(parent));
            addPassStage.setResizable(false);
            addPassStage.show();
            borderPane.setDisable(true);

            addPassStage.setOnCloseRequest(evt -> {
                openMainViewControlerOnClosing();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs current user out and returns to login window.
     *
     * @throws Exception
     */
    public void logoutButtonAction() throws Exception {
        logoutButton.getScene().getWindow().hide();
        PasswordManagerLauncher pml = new PasswordManagerLauncher();
        pml.start(new Stage());
    }

    /**
     * Adds an HBox that displays the domain, username button, and password button.
     *
     * @param internetAccount
     */
    void addPasswordHBox(InternetAccount internetAccount) {
        try {
            String hboxViewDir = PasswordManagerModel.VIEW_DIRECTORY + "UserHBoxView.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(hboxViewDir));
            HBox hbox = loader.load();
            UserHBoxController userHBoxController = loader.getController();

            passwordsVBox.getChildren().add(hbox);
            Label label = ((Label) hbox.getChildren().get(0));
            label.setText(internetAccount.getDomain());
            Tooltip tooltip1 = new Tooltip();
            tooltip1.setText("Click on me to redirect to the website!");
            Tooltip.install(label, tooltip1);

            ImageView icon = (ImageView) hbox.getChildren().get(1);
            if(internetAccount.getDomain().startsWith("https")) {
                icon.setImage(new javafx.scene.image.Image("https://cdn0.iconfinder.com/data/icons/essentials-solid-glyphs-vol-1/100/Security-Lock-Secure-Padlock-512.png"));
                Tooltip tooltip2 = new Tooltip();
                tooltip2.setText("This website is secure (https://)");
                Tooltip.install(icon, tooltip2);
            }

            HBox subHBox = (HBox) hbox.getChildren().get(2);
            ((Button) (subHBox.getChildren().get(0))).setText(internetAccount.getUserName());
            Button deleteButton = (Button) subHBox.getChildren().get(2); // get the delete button

            userHBoxController.initialize(internetAccount);

            reorderButton.setOnAction(event -> {
                System.out.println("Reorder button was pressed");
                if(clickCount%2 == 0) {
                    unfadeTransition.play();
                    userHBoxController.unsortDomain(user.getInternetAccountsList(),passwordsVBox);
                    reorderButton.setStyle("-fx-background-color: #C92D39;");
                    reorderButton.setText("Sort Domains");
                }
                else {
                    fadeTransition.play();
                    userHBoxController.sortDomain(user.getInternetAccountsList(),passwordsVBox);
                    reorderButton.setStyle("-fx-background-color: #c96f2d;");
                    reorderButton.setText("Unsort Domains");
                }
                clickCount++;
            });

            deleteButton.setOnAction(event -> {
                System.out.println("Delete button was pressed");
                passwordsVBox.getChildren().remove(hbox);
                user.removeInternetAccount(internetAccount);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void openMainViewControlerOnClosing() {
        borderPane.setDisable(false);
        addPassStage.close();
    }

    public void addGeneratedPasswordButtonAction() {
        System.out.println("Adding new password...");
        try {
            String viewPath = PasswordManagerModel.VIEW_DIRECTORY + "AddPassGeneratorView.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
            addPassStage = new Stage();
            Parent parent = loader.load();
            GeneratePassController genPassController = loader.getController();
            genPassController.initialize(this);
            addPassStage.setTitle("Generate password");
            addPassStage.setScene(new Scene(parent));
            addPassStage.setResizable(false);
            addPassStage.show();
            borderPane.setDisable(true);

            addPassStage.setOnCloseRequest(evt -> {
                openMainViewControlerOnClosing();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccButtonAction() {
        System.out.println("Delete Account?");
        try {
            String viewPath = PasswordManagerModel.VIEW_DIRECTORY + "DeleteAccView.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
            addPassStage = new Stage();
            Parent parent = loader.load();
            DeleteAccController deleteAccController = loader.getController();
            deleteAccController.initialize(this);
            addPassStage.setTitle("Delete account");
            addPassStage.setScene(new Scene(parent));
            addPassStage.setResizable(false);
            addPassStage.show();
            borderPane.setDisable(true);

            addPassStage.setOnCloseRequest(evt -> {
                openMainViewControlerOnClosing();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeUser() {
        model.removeUser(user);
    }

}
