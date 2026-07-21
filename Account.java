import java.util.ArrayList;

/**
 * Holds one person's balance and their transaction history.
 * Account doesn't know about usernames or PINs — that's User's job.
 */
public class Account {

    private final int accountNumber;
    private double balance;
    private final ArrayList<Transaction> history = new ArrayList<>();

    public Account(int accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        history.add(new Transaction("DEPOSIT", amount, "Money deposited"));
    }

    /**
     * @return true if the withdrawal succeeded, false if funds were insufficient
     */
    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        history.add(new Transaction("WITHDRAW", amount, "Money withdrawn"));
        return true;
    }

    /**
     * Moves money from this account to another account.
     * Both accounts get their own history entry so each side can see the transfer.
     * @return true if the transfer succeeded, false if funds were insufficient
     */
    public boolean transfer(Account receiver, double amount) {
        if (amount > balance) {
            return false;
        }

        balance -= amount;
        receiver.balance += amount;

        history.add(new Transaction("TRANSFER", amount,
                "Transferred to Account #" + receiver.accountNumber));
        receiver.history.add(new Transaction("TRANSFER", amount,
                "Received from Account #" + accountNumber));

        return true;
    }

    public void printHistory() {
        System.out.println();

        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        System.out.println("Transaction History");
        System.out.println("------------------------------");
        for (Transaction transaction : history) {
            System.out.println(transaction);
        }
    }
}