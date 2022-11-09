package launcher;

import javafx.application.Application;

/**
 * This class calls the PasswordManagerLauncher class. The main method is not in the launcher class because we did
 * not setup a module path. Please see:
 * <a href="https://stackoverflow.com/questions/55760343/error-javafx-runtime-components-are-missing-javafx-11-and-openjdk-11-and-ecli">click here</a>.
 *
 * @author Jesse Chen
 */

public class PasswordManager {
    public static void main(String[] args) {
        Application.launch(PasswordManagerLauncher.class);
    }
}
