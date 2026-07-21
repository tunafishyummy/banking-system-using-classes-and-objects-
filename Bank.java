// We use a list because the bank can have more users whenever somebody signs up
import java.util.ArrayList;

/*
 * This is basically the bank's little list of users, plus the code for finding them again
 */
public class Bank {

    // This is the bank's full list of people who made accounts
    private final ArrayList<User> users = new ArrayList<>();

    // Each new account grabs this number, then the next person gets a different one
    private int nextAccountNumber = 1000;

    // This puts together a user and an account at the same time during sign-up
    public User createUser(String username, String pin) {

        // Use the current number, then bump it up so the next account gets a new one
        Account account = new Account(nextAccountNumber++);

        // Connect the login details with the brand-new account
        User user = new User(username, pin, account);

        // Put this finished user into the bank's list so they can log in later
        users.add(user);

        // Send the new user back so Main can show their account number
        return user;

    }

    // Main uses this before creating someone so usernames stay unique
    public boolean usernameExists(String username) {

        // We need to check everyone here so two people don't end up sharing the same username
        for (User user : users) {

            // Treating Alex and alex as the same saves a pretty confusing login situation
            if (user.getUsername().equalsIgnoreCase(username)) {
                // We found one already, so Main needs to ask for a different name
                return true;
            }

        }

        // We checked the whole list and nobody had that username
        return false;

    }

    // This looks for the one user whose login details match what was typed
    public User login(String username, String pin) {

        // A login only works when both pieces match the same person
        for (User user : users) {

            if (user.getUsername().equalsIgnoreCase(username)
                    // Both checks have to be true, not just the username part
                    && user.getPin().equals(pin)) {

                // Give Main the matching user so it can open that person's menu
                return user;

            }

        }

        // No match means Main will treat this as a failed login
        return null;

    }

    // Transfers call this to turn an account number into the user who owns it
    public User findByAccountNumber(int accountNumber) {

        // Transfers use account numbers, so this finds the actual person behind one
        for (User user : users) {

            if (user.getAccount().getAccountNumber() == accountNumber) {
                // Found the account, so send back its owner
                return user;
            }

        }

        // This is the same no-match result as the login method earlier
        return null;

    }

    // This makes the transfer list without accidentally showing the sender as a choice
    public void printOtherAccounts(User currentUser) {

        // Same user-list loop as earlier
        for (User user : users) {

            // No point listing your own account as someone you can send money to
            if (user == currentUser) {
                // Jump straight to the next user when this one is the sender
                continue;
            }

            // Show the number Main needs for a transfer, plus a friendly name
            System.out.printf("%d - %s%n",
                    // This gets the number from this user's account
                    user.getAccount().getAccountNumber(),
                    // This is the name paired with that account number
                    user.getUsername());

        }

    }

}
