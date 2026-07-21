/*
 * One user is the login info plus the account that belongs to them
 */
public class User {

    // This is the name the person types when they log in
    private final String username;

    // This is the four-digit check that goes with the username
    private final String pin;

    // This is the one bank account this user owns
    private final Account account;

    // Bank calls this after it has made both the login details and the account
    public User(String username, String pin, Account account) {

        // Keep the username so login can compare it later
        this.username = username;

        // Same saving idea as the username above, but for the PIN
        this.pin = pin;

        // Same saving idea as earlier, now connecting the account to its owner
        this.account = account;

    }

    // Bank and Main call this whenever they need to see the login name
    public String getUsername() {
        // Give back the saved username
        return username;
    }

    // Bank uses this while checking whether a login is correct
    public String getPin() {
        // Same return idea as the username getter above
        return pin;
    }

    // Main and Bank use this to reach the user's money and account number
    public Account getAccount() {
        // Same return idea as the getters earlier
        return account;
    }

}
