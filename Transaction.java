import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Represents one banking transaction.
 */
public class Transaction {

    // What kind of transaction this is.
    private final TransactionType type;

    // Money involved.
    private final double amount;

    // Small description.
    private final String description;

    // Time the transaction happened.
    private final LocalDateTime timestamp;

    /*
     * Creates a transaction.
     */
    public Transaction(TransactionType type,
                       double amount,
                       String description) {

        // Save the values.
        this.type = type;
        this.amount = amount;
        this.description = description;

        // Record the current time.
        timestamp = LocalDateTime.now();

    }

    /*
     * Converts the transaction into readable text.
     */
    @Override
    public String toString() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss");

        return String.format(
                "[%s] %-8s PHP %.2f - %s",
                timestamp.format(formatter),
                type,
                amount,
                description
        );

    }

}