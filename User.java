/*
 * Represents one registered bank user.
 */
public class User {

    // Store the user's username.
    private final String username;

    // Store the user's login PIN.
    private final String pin;

    // Every user owns exactly one account.
    private final Account account;

    /*
     * Creates a new user.
     */
    public User(String username, String pin, Account account) {

        // Save the username.
        this.username = username;

        // Save the PIN.
        this.pin = pin;

        // Save the user's account.
        this.account = account;

    }

    /*
     * Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /*
     * Returns the PIN.
     */
    public String getPin() {
        return pin;
    }

    /*
     * Returns the user's account.
     */
    public Account getAccount() {
        return account;
    }

}