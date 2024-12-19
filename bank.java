import java.util.Scanner;
class Main {
    private static Bank bank;
    private static Scanner scanner;

    public static void main(String[] args) {
        bank = new Bank();
        scanner = new Scanner(System.in);

        System.out.println("Welcome to the Bank!");

        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.print("Please select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    viewTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using our service!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();  

        Account newAccount = bank.createAccount(name, accountNumber);
        if (newAccount != null) {
            System.out.println("Account created successfully!");
            System.out.println(newAccount);
        }
    }

    private static void depositMoney() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        Account account = bank.getAccount(accountNumber);

        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
            System.out.println("Deposit successful. New balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void withdrawMoney() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        Account account = bank.getAccount(accountNumber);

        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            if (account.withdraw(amount)) {
                System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
            }
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void checkBalance() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        Account account = bank.getAccount(accountNumber);

        if (account != null) {
            System.out.println("Account balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void viewTransactionHistory() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        Account account = bank.getAccount(accountNumber);

        if (account != null) {
            account.printTransactionHistory();
        } else {
            System.out.println("Account not found!");
        }
    }
}
