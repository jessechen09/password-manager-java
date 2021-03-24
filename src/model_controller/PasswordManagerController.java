package model_controller;

import user.User;

/**
 * @author jessechen
 */
public class PasswordManagerController {

    private PasswordManagerModel model;

    public PasswordManagerController(PasswordManagerModel model) {
        this.model = model;
    }

    public boolean hasUser(String username) {
        return model.hasUser(username);
    }

    public boolean isCorrectPassword(String username, String enteredPassword) {
        if (!model.hasUser(username)) return false;
        return model.getUser(username).getAccount().getPassword().equals(enteredPassword);
    }

    public void setUser(User user) {
        model.setUser(user);
    }

    public User getUser(String username) {
        return model.getUser(username);
    }

    public String getCurrentUserName() {return model.getCurrentUserName(); }

}
