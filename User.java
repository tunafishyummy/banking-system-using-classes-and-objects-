/**
 * A User is the login info (username + PIN) paired with the one
 * Account that belongs to them.
 */
public class User {

    private final String username;
    private final String pin;
    private final Account account;

    public User(String username, String pin, Account account) {
        this.username = username;
        this.pin = pin;
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public String getPin() {
        return pin;
    }

    public Account getAccount() {
        return account;
    }
}