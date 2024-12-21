import java.util.*;

// Transaction class to store details of each transaction
class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + ": $" + amount;
    }
}

// Account class to manage individual user accounts
class Account {
    private String accountHolder;
    private int accountNumber;
    private double balance;
    private List<Transaction> transactionHistory;

    public Account(String accountHolder, int accountNumber) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
            System.out.println("Deposit successful! Current Balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
            System.out.println("Withdrawal successful! Current Balance: $" + balance);
        } else {
            System.out.println("Insufficient funds or invalid amount!");
        }
    }

    public void printTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction History for Account " + accountNumber + ":");
            for (Transaction transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    @Override
    public String toString() {
        return "Account Holder: " + accountHolder + ", Account Number: " + accountNumber + ", Balance: $" + balance;
    }
}

// Main class to manage the banking system {
class Main {
    private static Map<Integer, Account> accounts = new HashMap<>();
    private static int nextAccountNumber = 1000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Banking System Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    depositMoney(scanner);
                    break;
                case 3:
                    withdrawMoney(scanner);
                    break;
                case 4:
                    checkBalance(scanner);
                    break;
                case 5:
                    viewTransactionHistory(scanner);
                    break;
                case 6:
                    System.out.println("Exiting... Thank you for using the banking system!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();
        int accountNumber = nextAccountNumber++;
        Account account = new Account(name, accountNumber);
        accounts.put(accountNumber, account);
        System.out.println("Account created successfully! Your account number is " + accountNumber);
    }

    private static void depositMoney(Scanner scanner) {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void withdrawMoney(Scanner scanner) {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void checkBalance(Scanner scanner) {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Current Balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void viewTransactionHistory(Scanner scanner) {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.printTransactionHistory();
        } else {
            System.out.println("Account not found!");
        }
    }
}
