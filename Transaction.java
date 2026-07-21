// This gives us the date and time right when a transaction is made
import java.time.LocalDateTime;
// This lets us make that date and time look nicer when we print it
import java.time.format.DateTimeFormatter;

/*
 * This is one saved receipt for something that happened with the account
 */
public class Transaction {

    // This says whether the money was deposited, withdrawn, or transferred
    private final TransactionType type;

    // This is how much money the transaction was for
    private final double amount;

    // This is the short extra message like where a transfer went
    private final String description;

    // Saving the time now means the history says when it happened instead of just showing a mystery entry
    private final LocalDateTime timestamp;

    // Account gives us these three details whenever it saves a new history record
    public Transaction(TransactionType type,
                       // This is the same amount from deposit, withdraw, or transfer
                       double amount,
                       // This is the same small history message from earlier
                       String description) {

        // Save the transaction category for later printing
        this.type = type;
        // Save the money amount too
        this.amount = amount;
        // Save the little explanation that goes beside it
        this.description = description;

        // Grab the current moment once, so the timestamp doesn't change later
        timestamp = LocalDateTime.now();

    }

    // This replaces Java's default object text with a useful transaction line
    @Override
    public String toString() {

        // Pick a date style that's easier for a person to read than the raw Java one
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss");

        // Put all the saved pieces together into one neat line for the history list
        return String.format(
                // These spots get filled by the values underneath, kind of like a template
                "[%s] %-8s PHP %.2f - %s",
                // Turn the saved time into the format we picked above
                timestamp.format(formatter),
                // This inserts the transaction type
                type,
                // This inserts the amount with two decimal places
                amount,
                // This inserts the extra explanation
                description
        );

    }

}
