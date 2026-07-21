import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A single record of something that happened to an account
 * (a deposit, withdrawal, or transfer). Once created, a Transaction
 * never changes — it's just a receipt.
 */
public class Transaction {

    private final String type;
    private final double amount;
    private final String description;
    private final LocalDateTime timestamp;

    public Transaction(String type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Formats this transaction as one readable line for the history list,
     * e.g.: [Jul 21 2026 14:03:10] DEPOSIT  PHP 500.00 - Money deposited
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss");
        return String.format("[%s] %-8s PHP %.2f - %s",
                timestamp.format(formatter), type, amount, description);
    }
}