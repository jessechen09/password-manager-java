/**
 * 
 * The InternetAccount object holds all the info for the account login and
 * passwords that the password manager will save.
 * 
 * @author jessechen
 *
 */
public class InternetAccount extends Account {

    private String domain;

    public InternetAccount(String domain, String username, String password) {
        super(username, password);
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    public void changeDomain(String domain) {
        this.domain = domain;
    }
}
