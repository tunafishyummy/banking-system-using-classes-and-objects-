import java.util.Scanner;

/*
 * Main class for the banking system.
 * This class handles all menus and user interaction.
 */
public class Main {

    // Scanner used everywhere for reading user input.
    private static final Scanner scanner = new Scanner(System.in);

    // The single Bank object that stores every user.
    private static final Bank bank = new Bank();

    public static void main(String[] args) {

        // Keep running until the user chooses to exit.
        while (true) {

            printMainMenu();

            // Read a valid menu choice.
            int choice = readInt("Choose an option: ", 1, 3);

            switch (choice) {

                case 1 -> createAccount();

                case 2 -> login();

                case 3 -> {
                    System.out.println("\nThanks for using Simple Bank!");
                    scanner.close();
                    return;
                }

            }

        }

    }

    /*
     * Prints the main menu.
     */
    private static void printMainMenu() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("        SIMPLE BANKING SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Create Account");
        System.out.println("2. Log In");
        System.out.println("3. Exit");
        System.out.println();

    }

    /*
     * Lets the user create a new account.
     */
    private static void createAccount() {

        System.out.println("\n--- Create Account ---");

        String username;

        // Keep asking until the username is unique.
        while (true) {

            System.out.print("Username: ");
            username = scanner.nextLine().trim();

            if (username.isBlank()) {
                System.out.println("Username can't be empty.");
                continue;
            }

            if (bank.usernameExists(username)) {
                System.out.println("That username already exists.");
                continue;
            }

            break;

        }

        String pin;

        // Keep asking until the PIN is valid.
        while (true) {

            System.out.print("4-digit PIN: ");
            pin = scanner.nextLine().trim();

            if (!pin.matches("\\d{4}")) {
                System.out.println("PIN must be exactly 4 digits.");
                continue;
            }

            break;

        }

        User user = bank.createUser(username, pin);

        System.out.println();
        System.out.println("Account created successfully!");
        System.out.println("Account Number: " + user.getAccount().getAccountNumber());
        System.out.println("Starting Balance: PHP 0.00");

    }

    /*
     * Lets a user log into their account.
     */
    private static void login() {

        System.out.println("\n--- Log In ---");

        System.out.print("Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("PIN: ");
        String pin = scanner.nextLine().trim();

        User user = bank.login(username, pin);

        if (user == null) {

            System.out.println("Incorrect username or PIN.");
            return;

        }

        System.out.println("\nWelcome, " + user.getUsername() + "!");

        accountMenu(user);

    }

    /*
     * Shows the logged-in menu.
     */
    private static void accountMenu(User user) {

        while (true) {

            System.out.println();
            System.out.println("----------------------------------------");
            System.out.println("Logged in as: " + user.getUsername());
            System.out.println("----------------------------------------");
            System.out.println("1. View Account Information");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Transfer Money");
            System.out.println("6. View Transaction History");
            System.out.println("7. Log Out");
            System.out.println();

            int choice = readInt("Choose an option: ", 1, 7);

            switch (choice) {

                case 1 -> {

                    System.out.println("\nAccount Number : "
                            + user.getAccount().getAccountNumber());

                    System.out.println("Username       : "
                            + user.getUsername());

                    System.out.printf("Balance        : PHP %.2f%n",
                            user.getAccount().getBalance());

                }

                case 2 -> {

                    System.out.printf("%nCurrent Balance: PHP %.2f%n",
                            user.getAccount().getBalance());

                }

                case 3 -> {

                    double amount = readAmount("Deposit amount: ");

                    user.getAccount().deposit(amount);

                    System.out.println("Deposit successful!");

                }

                case 4 -> {

                    double amount = readAmount("Withdraw amount: ");

                    if (user.getAccount().withdraw(amount)) {

                        System.out.println("Withdrawal successful!");

                    } else {

                        System.out.println("Insufficient funds.");

                    }

                }

                case 5 -> transfer(user);

                case 6 -> user.getAccount().printHistory();

                case 7 -> {

                    System.out.println("Logged out.");
                    return;

                }

            }

        }

    }

    /*
     * Transfers money to another account.
     */
    private static void transfer(User sender) {

        System.out.println("\nAvailable Accounts:");

        bank.printOtherAccounts(sender);

        int accountNumber = readInt(
                "\nDestination account number: ",
                1000,
                Integer.MAX_VALUE
        );

        User receiver = bank.findByAccountNumber(accountNumber);

        if (receiver == null || receiver == sender) {

            System.out.println("Invalid account.");
            return;

        }

        double amount = readAmount("Amount: ");

        if (sender.getAccount().transfer(receiver.getAccount(), amount)) {

            System.out.println("Transfer successful!");

        } else {

            System.out.println("Transfer failed.");

        }

    }

    /*
     * Reads an integer within a range.
     */
    private static int readInt(String prompt, int min, int max) {

        while (true) {

            System.out.print(prompt);

            try {

                int value = Integer.parseInt(scanner.nextLine());

                if (value >= min && value <= max) {

                    return value;

                }

            } catch (NumberFormatException ignored) {
            }

            System.out.println("Please enter a valid option.");

        }

    }

    /*
     * Reads a positive money amount.
     */
    private static double readAmount(String prompt) {

        while (true) {

            System.out.print(prompt);

            try {

                double amount = Double.parseDouble(scanner.nextLine());

                if (amount > 0) {

                    return amount;

                }

            } catch (NumberFormatException ignored) {
            }

            System.out.println("Enter a valid positive amount.");

        }

    }

}