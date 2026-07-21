/*
 * These are the only kinds of money movement our little bank knows about for now
 */
// An enum is just a fixed list, so nobody can accidentally invent a weird fourth transaction type
public enum TransactionType {

    // Money came into the account
    DEPOSIT,
    // Money was taken out of the account
    WITHDRAW,
    // Money moved from one account to another
    TRANSFER

}
