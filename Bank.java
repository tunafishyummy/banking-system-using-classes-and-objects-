import java.util.ArrayList;

/**
 * Keeps the list of every registered User and hands out account numbers.
 * Think of Bank as the "directory" — it doesn't hold money itself,
 * it just knows where to find each person's Account.
 */
public class Bank {

    private final ArrayList<User> users = new ArrayList<>();
    private int nextAccountNumber = 1000;

    /** Creates a new Account + User pair and registers it with the bank. */
    public User createUser(String username, String pin) {
        Account account = new Account(nextAccountNumber++);
        User user = new User(username, pin, account);
        users.add(user);
        return user;
    }

    public boolean usernameExists(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    /** @return the matching User, or null if the username/PIN combo is wrong */
    public User login(String username, String pin) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username) && user.getPin().equals(pin)) {
                return user;
            }
        }
        return null;
    }

    /** @return the User who owns this account number, or null if none found */
    public User findByAccountNumber(int accountNumber) {
        for (User user : users) {
            if (user.getAccount().getAccountNumber() == accountNumber) {
                return user;
            }
        }
        return null;
    }

    /** Prints every account EXCEPT the current user's own — used for the transfer menu. */
    public void printOtherAccounts(User currentUser) {
        for (User user : users) {
            if (user == currentUser) {
                continue;
            }
            System.out.printf("%d - %s%n", user.getAccount().getAccountNumber(), user.getUsername());
        }
    }
}