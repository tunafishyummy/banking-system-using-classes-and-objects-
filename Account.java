import java.util.ArrayList;

/*
 * Represents a single bank account.
 */
public class Account {

    // Unique account number.
    private final int accountNumber;

    // Current balance.
    private double balance;

    // Store every transaction.
    private final ArrayList<Transaction> history = new ArrayList<>();

    /*
     * Creates a new account.
     */
    public Account(int accountNumber) {

        // Save the account number.
        this.accountNumber = accountNumber;

        // New accounts always start with zero money.
        balance = 0;

    }

    /*
     * Returns the account number.
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /*
     * Returns the current balance.
     */
    public double getBalance() {
        return balance;
    }

    /*
     * Adds money to the account.
     */
    public void deposit(double amount) {

        // Increase the balance.
        balance += amount;

        // Save the transaction.
        history.add(new Transaction(
                TransactionType.DEPOSIT,
                amount,
                "Money deposited"
        ));

    }

    /*
     * Removes money from the account.
     */
    public boolean withdraw(double amount) {

        // Don't allow overdrafts.
        if (amount > balance) {
            return false;
        }

        // Reduce the balance.
        balance -= amount;

        // Save the transaction.
        history.add(new Transaction(
                TransactionType.WITHDRAW,
                amount,
                "Money withdrawn"
        ));

        return true;

    }

    /*
     * Transfers money to another account.
     */
    public boolean transfer(Account receiver, double amount) {

        // Make sure enough money is available.
        if (amount > balance) {
            return false;
        }

        // Remove money from this account.
        balance -= amount;

        // Add money to the receiving account.
        receiver.balance += amount;

        // Save the sender's transaction.
        history.add(new Transaction(
                TransactionType.TRANSFER,
                amount,
                "Transferred to Account #" + receiver.accountNumber
        ));

        // Save the receiver's transaction.
        receiver.history.add(new Transaction(
                TransactionType.TRANSFER,
                amount,
                "Received from Account #" + accountNumber
        ));

        return true;

    }

    /*
     * Prints every saved transaction.
     */
    public void printHistory() {

        System.out.println();

        if (history.isEmpty()) {

            System.out.println("No transactions yet.");
            return;

        }

        System.out.println("Transaction History");
        System.out.println("------------------------------");

        // Print each transaction.
        for (Transaction transaction : history) {
            System.out.println(transaction);
        }

    }

}