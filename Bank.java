import java.util.ArrayList;

/*
 * Stores every user in the banking system.
 * Also handles finding users and creating new accounts.
 */
public class Bank {

    // Store every registered user.
    private final ArrayList<User> users = new ArrayList<>();

    // Keep track of the next account number to assign.
    private int nextAccountNumber = 1000;

    /*
     * Creates a new user and adds them to the bank.
     */
    public User createUser(String username, String pin) {

        // Create a new account with the next available number.
        Account account = new Account(nextAccountNumber++);

        // Create the user that owns the account.
        User user = new User(username, pin, account);

        // Save the new user.
        users.add(user);

        // Give the created user back to the caller.
        return user;

    }

    /*
     * Checks if a username already exists.
     */
    public boolean usernameExists(String username) {

        // Look through every user.
        for (User user : users) {

            // Compare usernames without caring about case.
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }

        }

        // No matching username was found.
        return false;

    }

    /*
     * Attempts to log in with a username and PIN.
     */
    public User login(String username, String pin) {

        // Check every registered user.
        for (User user : users) {

            // Make sure both the username and PIN match.
            if (user.getUsername().equalsIgnoreCase(username)
                    && user.getPin().equals(pin)) {

                return user;

            }

        }

        // Login failed.
        return null;

    }

    /*
     * Finds a user by their account number.
     */
    public User findByAccountNumber(int accountNumber) {

        // Search through every user.
        for (User user : users) {

            // Check if this account number matches.
            if (user.getAccount().getAccountNumber() == accountNumber) {
                return user;
            }

        }

        // Nothing matched.
        return null;

    }

    /*
     * Prints every account except the logged-in user's.
     */
    public void printOtherAccounts(User currentUser) {

        // Go through every registered user.
        for (User user : users) {

            // Skip the currently logged-in user.
            if (user == currentUser) {
                continue;
            }

            // Show the account number and username.
            System.out.printf("%d - %s%n",
                    user.getAccount().getAccountNumber(),
                    user.getUsername());

        }

    }

}