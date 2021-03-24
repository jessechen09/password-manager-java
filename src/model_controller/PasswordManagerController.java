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
        return model.getUserMap().containsKey(username);
    }

    public boolean isCorrectPassword(String username, String enteredPassword) {
        if (!hasUser(username)) return false;
        return getUser(username).getAccount().getPassword().equals(enteredPassword);
    }

    public void setUser(User user) {
        model.setUser(user);
    }

    public User getUser(String username) {
        return model.getUserMap().get(username);
    }

    public String getCurrentUserName() {
        return model.getCurrentUser().getAccount().getUserName();
    }

}
