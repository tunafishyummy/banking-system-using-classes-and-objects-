// Scanner is what lets the terminal program read what the user types
import java.util.Scanner;

/*
 * Most of the terminal stuff lives here, like the menus and asking the user questions
 */
public class Main {

    // One shared scanner is enough since every menu reads from the same keyboard
    private static final Scanner scanner = new Scanner(System.in);

    // One bank sticks around for the whole program, so accounts don't disappear between menus
    private static final Bank bank = new Bank();

    // Java starts running the program here
    public static void main(String[] args) {

        // After each menu choice we're back here again, unless they picked Exit
        while (true) {

            // Show the first menu again whenever we come back around
            printMainMenu();

            // Only allow the three choices that are actually on this menu
            int choice = readInt("Choose an option: ", 1, 3);

            // Send the chosen number to the matching bit of code
            switch (choice) {

                // Choice one starts the sign-up process
                case 1 -> createAccount();

                // Choice two asks for login details
                case 2 -> login();

                // Choice three shuts the program down nicely
                case 3 -> {
                    // Say goodbye before closing everything
                    System.out.println("\nThanks for using Simple Bank!");
                    // Close the keyboard reader since we're fully done with it
                    scanner.close();
                    // This leaves main, so the while loop stops too
                    return;
                }

            }

        }

    }

    // This keeps the main menu in one place instead of repeating all these lines
    private static void printMainMenu() {

        // Just gives the menu a little breathing room
        System.out.println();
        // Top border for the menu box
        System.out.println("========================================");
        // The menu title
        System.out.println("        SIMPLE BANKING SYSTEM");
        // Same border as the line above
        System.out.println("========================================");
        // First thing the user can choose
        System.out.println("1. Create Account");
        // Second thing the user can choose
        System.out.println("2. Log In");
        // Last thing the user can choose
        System.out.println("3. Exit");
        // Same empty-line spacing as earlier
        System.out.println();

    }

    // This gathers the details Bank needs before it makes a new user
    private static void createAccount() {

        // Let the user know which part of the program they're in
        System.out.println("\n--- Create Account ---");

        // We declare this outside the loop because we'll use the final good name later
        String username;

        // This keeps looping until the name is usable, because duplicate usernames would mess up login
        while (true) {

            // Ask for a name without jumping to the next terminal line yet
            System.out.print("Username: ");
            // trim removes accidental spaces at either end
            username = scanner.nextLine().trim();

            // Blank usernames would make logging in pretty weird
            if (username.isBlank()) {
                // Tell them why we're asking again
                System.out.println("Username can't be empty.");
                // Go back to the top of this username loop
                continue;
            }

            // Bank checks this name against the people who already signed up
            if (bank.usernameExists(username)) {
                // Same helpful error-message idea as earlier
                System.out.println("That username already exists.");
                // Same retry move as the blank-name check above
                continue;
            }

            // The name passed both checks, so we're done with this loop
            break;

        }

        // Same setup as the username, but now we're waiting for a good PIN
        String pin;

        // A four-digit PIN is the rule here, so we don't create an account we can't log into properly
        while (true) {

            // Ask for the four digits
            System.out.print("4-digit PIN: ");
            // Same trimming idea as the username earlier
            pin = scanner.nextLine().trim();

            // The pattern means exactly four numbers, no letters or extra digits sneaking in
            // Check the typed PIN against the four-digit rule before accepting it
            if (!pin.matches("\\d{4}")) {
                // Explain the rule before trying again
                System.out.println("PIN must be exactly 4 digits.");
                // Same retry move as the username loop earlier
                continue;
            }

            // The PIN is good, so we can finally create the account
            break;

        }

        // Bank does the actual setup and gives us the finished user back
        User user = bank.createUser(username, pin);

        // Same little spacing line as earlier
        System.out.println();
        // Confirm that the sign-up worked
        System.out.println("Account created successfully!");
        // Show the number they need for transfers later
        System.out.println("Account Number: " + user.getAccount().getAccountNumber());
        // New accounts always begin with no money
        System.out.println("Starting Balance: PHP 0.00");

    }

    // This checks the typed username and PIN, then opens the right account menu
    private static void login() {

        // Show the current part of the program
        System.out.println("\n--- Log In ---");

        // Ask for the username first
        System.out.print("Username: ");
        // Same input-and-trim step as account creation earlier
        String username = scanner.nextLine().trim();

        // Then ask for the PIN
        System.out.print("PIN: ");
        // Same input-and-trim step as the username above
        String pin = scanner.nextLine().trim();

        // Bank checks both details against the saved users
        User user = bank.login(username, pin);

        // null is Bank's way of saying it couldn't find a matching user
        if (user == null) {

            // Don't say which part was wrong, just like a real login screen usually does
            System.out.println("Incorrect username or PIN.");
            // Go back to the main menu
            return;

        }

        // The login worked, so use the saved username in the greeting
        System.out.println("\nWelcome, " + user.getUsername() + "!");

        // Hand this exact user to the menu that changes their account
        accountMenu(user);

    }

    // This is the menu that keeps running until the logged-in person logs out
    private static void accountMenu(User user) {

        // Same keep-showing-the-menu loop as main earlier
        while (true) {

            // Start the account menu on a fresh line
            System.out.println();
            // Top divider for this menu
            System.out.println("----------------------------------------");
            // Remind us whose account we're using
            System.out.println("Logged in as: " + user.getUsername());
            // Same divider as the one above
            System.out.println("----------------------------------------");
            // Menu choice one
            System.out.println("1. View Account Information");
            // Menu choice two
            System.out.println("2. Check Balance");
            // Menu choice three
            System.out.println("3. Deposit Money");
            // Menu choice four
            System.out.println("4. Withdraw Money");
            // Menu choice five
            System.out.println("5. Transfer Money");
            // Menu choice six
            System.out.println("6. View Transaction History");
            // Menu choice seven
            System.out.println("7. Log Out");
            // Same spacing line as earlier
            System.out.println();

            // Only accept numbers that belong to this bigger menu
            int choice = readInt("Choose an option: ", 1, 7);

            // Pick the account action that matches the typed number
            switch (choice) {

                // Choice one shows all the basic details saved for this account
                case 1 -> {

                    // Show the account number across two lines so it stays readable
                    System.out.println("\nAccount Number : "
                            // This grabs the number from the user's account object
                            + user.getAccount().getAccountNumber());

                    // Same split-line printing idea as the account number above
                    System.out.println("Username       : "
                            // This gets the person's saved username
                            + user.getUsername());

                    // %.2f makes money always show two decimal places
                    System.out.printf("Balance        : PHP %.2f%n",
                            // This is the balance we want to put into that money spot
                            user.getAccount().getBalance());

                }

                // Choice two only shows the balance when they don't need the other details
                case 2 -> {

                    // This is the shorter balance-only version of choice one
                    System.out.printf("%nCurrent Balance: PHP %.2f%n",
                            // Same balance getter as earlier
                            user.getAccount().getBalance());

                }

                // Choice three asks for money to add, then sends it to the account
                case 3 -> {

                    // readAmount makes sure nobody deposits a zero or weird text amount
                    double amount = readAmount("Deposit amount: ");

                    // Let the Account object update its own balance and history
                    user.getAccount().deposit(amount);

                    // Confirm that the deposit was accepted
                    System.out.println("Deposit successful!");

                }

                // Choice four asks for money to take out, as long as the balance can cover it
                case 4 -> {

                    // Same checked-money input as the deposit earlier
                    double amount = readAmount("Withdraw amount: ");

                    // withdraw returns false if the balance isn't big enough
                    if (user.getAccount().withdraw(amount)) {

                        // This is the successful side of the withdrawal check
                        System.out.println("Withdrawal successful!");

                    } else {

                        // This is the not-enough-money side of that same check
                        System.out.println("Insufficient funds.");

                    }

                }

                // Transfers have enough steps to live in their own method
                case 5 -> transfer(user);

                // Account already knows how to print the history it saved
                case 6 -> user.getAccount().printHistory();

                case 7 -> {

                    // Let the person know the account menu is closing
                    System.out.println("Logged out.");
                    // This returns to the main menu loop
                    return;

                }

            }

        }

    }

    // This asks where the money should go, then lets Account handle the actual move
    private static void transfer(User sender) {

        // Give the sender a list of possible accounts first
        System.out.println("\nAvailable Accounts:");

        // Bank leaves the sender out of this list so they can't pick themselves
        bank.printOtherAccounts(sender);

        // readInt keeps the entered account number in a sensible range
        int accountNumber = readInt(
                // This is the terminal prompt
                "\nDestination account number: ",
                // Bank starts giving out account numbers at 1000
                1000,
                // There's no practical upper limit for this little example
                Integer.MAX_VALUE
        );

        // Turn the number they typed into the user who owns that account
        User receiver = bank.findByAccountNumber(accountNumber);

        // null means the number wasn't found, and the second check blocks sending to yourself
        if (receiver == null || receiver == sender) {

            // Tell them this recipient can't be used
            System.out.println("Invalid account.");
            // Go back to the account menu without moving any money
            return;

        }

        // Same positive-money check as deposits and withdrawals earlier
        double amount = readAmount("Amount: ");

        // Account updates both balances and both histories if there's enough money
        if (sender.getAccount().transfer(receiver.getAccount(), amount)) {

            // This is the successful result of that transfer check
            System.out.println("Transfer successful!");

        } else {

            // This happens when the sender's balance was too low
            System.out.println("Transfer failed.");

        }

    }

    // This is the reusable helper for menu choices and account numbers
    private static int readInt(String prompt, int min, int max) {

        // We stay here until the input is actually a number in the menu's allowed range
        while (true) {

            // Show whichever question the caller gave us
            System.out.print(prompt);

            // try lets bad text fail safely instead of crashing the whole program
            try {

                // Turn what they typed into a normal whole number
                int value = Integer.parseInt(scanner.nextLine());

                // Make sure the number fits the caller's allowed limits
                if (value >= min && value <= max) {

                    // It passed both checks, so hand it back to the caller
                    return value;

                }

            // This catches things like letters, and we can just ask again
            } catch (NumberFormatException ignored) {
                // There is nothing extra to do here because the message below handles the bad input
            }

            // The input was either not a number or outside the allowed range
            System.out.println("Please enter a valid option.");

        }

    }

    // This is the reusable helper for any amount of money the user enters
    private static double readAmount(String prompt) {

        // Money has to be more than zero, otherwise depositing nothing would be pretty pointless
        while (true) {

            // Same prompt-printing idea as readInt earlier
            System.out.print(prompt);

            // Same safe-conversion setup as readInt, but doubles can have decimals
            try {

                // Turn input like 25.50 into a number Java can do money math with
                double amount = Double.parseDouble(scanner.nextLine());

                // Amounts must be positive so zero and negative money don't get through
                if (amount > 0) {

                    // This is a good amount, so give it back to deposit, withdraw, or transfer
                    return amount;

                }

            // Same bad-text catch as readInt earlier
            } catch (NumberFormatException ignored) {
                // Same empty catch idea as readInt, with the retry message right below
            }

            // Explain why we're looping around for another try
            System.out.println("Enter a valid positive amount.");

        }

    }

}
