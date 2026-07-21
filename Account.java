// This list type lets us keep adding transaction records as the account gets used
import java.util.ArrayList;

/*
 * This is where one account keeps its money and the stuff that's happened to it
 */
public class Account {

    // This is the account's ID, and final means it won't randomly change later
    private final int accountNumber;

    // This is the actual money currently sitting in the account
    private double balance;

    // Keeping this means the user can look back at deposits, withdrawals, and transfers later
    private final ArrayList<Transaction> history = new ArrayList<>();

    // When Bank makes an account, it hands over the number it already picked
    public Account(int accountNumber) {

        // Save that number inside this specific account
        this.accountNumber = accountNumber;

        // New accounts start with no money, which is probably for the best
        balance = 0;

    }

    // Other classes need the number when they show or search for an account
    public int getAccountNumber() {
        // Give back the number we saved above
        return accountNumber;
    }

    // This lets the menu look at the balance without changing it
    public double getBalance() {
        // Same saved balance as earlier
        return balance;
    }

    // Depositing is just putting a positive amount into this account
    public void deposit(double amount) {

        // Add the incoming money onto whatever was already there
        balance += amount;

        // The balance changed, so we're saving a receipt for it too basically
        // Make a new receipt-like object and add it to our saved list
        history.add(new Transaction(
                // This label says what kind of transaction it was
                TransactionType.DEPOSIT,
                // This is the amount that just got added
                amount,
                // This is the little message the history will show
                "Money deposited"
        ));

    }

    // This returns true when the withdrawal works and false when it can't
    public boolean withdraw(double amount) {

        // Can't take out money that isn't there, because negative money would be kind of a problem
        if (amount > balance) {
            // Tell the menu it failed so it can show the right message
            return false;
        }

        // Take the requested amount away after we know it's safe
        balance -= amount;

        // Same deal as deposits, we want this to show up in the history
        history.add(new Transaction(
                // Same transaction label idea as earlier, but this time money left
                TransactionType.WITHDRAW,
                // Same amount from the method input as earlier
                amount,
                // This is what the user sees in their history
                "Money withdrawn"
        ));

        // Tell the caller the withdrawal went through okay
        return true;

    }

    // A transfer moves money between two Account objects and tells us if it worked
    public boolean transfer(Account receiver, double amount) {

        // Check first so we don't change either account when the sender is short on money
        if (amount > balance) {
            // Same failed result as the withdrawal earlier
            return false;
        }

        // Remove it from the sender first now that the check passed
        balance -= amount;

        // Put that exact same amount into the other person's account
        receiver.balance += amount;

        // Both people get their own record, otherwise the transfer would look a little suspicious later
        history.add(new Transaction(
                // This time the receipt says it was a transfer
                TransactionType.TRANSFER,
                // Same transfer amount as earlier
                amount,
                // Add the receiver's number so the sender knows where it went
                "Transferred to Account #" + receiver.accountNumber
        ));

        // Give the receiver their own receipt too, since both sides should see it
        receiver.history.add(new Transaction(
                // Same transfer type as the sender's receipt above
                TransactionType.TRANSFER,
                // Same amount as the sender's transfer above
                amount,
                // This message flips the point of view for the receiver
                "Received from Account #" + accountNumber
        ));

        // Everything worked, so let the menu know the transfer succeeded
        return true;

    }

    // This prints the saved transaction list for whichever account is logged in
    public void printHistory() {

        // Just makes a little empty line before the history starts
        System.out.println();

        // An empty list needs a different message instead of a blank table
        if (history.isEmpty()) {

            // Let the user know nothing has happened on this account yet
            System.out.println("No transactions yet.");
            // Stop here because there is nothing left to loop through
            return;

        }

        // This is the heading for the records below
        System.out.println("Transaction History");
        // Same heading decoration idea as earlier in the menus
        System.out.println("------------------------------");

        // Go through the saved list one at a time so every transaction gets shown
        for (Transaction transaction : history) {
            // Transaction's toString method turns this one record into readable text
            System.out.println(transaction);
        }

    }

}
