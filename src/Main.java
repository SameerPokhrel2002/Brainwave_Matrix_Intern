import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            // Getting user choice
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // Create Account
                    System.out.print("Enter new account number: ");
                    String accNum = scanner.nextLine();
                    System.out.print("Set PIN (4 digits): ");
                    int pin = scanner.nextInt();
                    atm.createAccount(accNum, pin);
                    break;

                case 2: // Login
                    System.out.print("Enter account number: ");
                    String loginAccNum = scanner.nextLine();
                    System.out.print("Enter PIN: ");
                    int loginPin = scanner.nextInt();
                    scanner.nextLine();
                    Account loggedInAccount = atm.authenticateUser(loginAccNum, loginPin);
                    if (loggedInAccount != null) {
                        System.out.println("Login successful! Welcome, " + loggedInAccount.getAccountNumber());
                        while (true) {
                            System.out.println("\nAccount Menu:");
                            System.out.println("1. Check Balance");
                            System.out.println("2. Deposit Money");
                            System.out.println("3. Withdraw Money");
                            System.out.println("4. Logout");
                            System.out.print("Choose an option: ");

                            int option = scanner.nextInt();
                            scanner.nextLine();
                            switch (option) {
                                case 1:
                                    System.out.printf("Your current balance is: $%.2f%n", loggedInAccount.getBalance());
                                    break;
                                case 2:
                                    System.out.print("Enter amount to deposit: $");
                                    double depositAmount = scanner.nextDouble();
                                    scanner.nextLine();
                                    if (depositAmount > 0) {
                                        double newBalance = loggedInAccount.getBalance() + depositAmount;
                                        loggedInAccount.setBalance(newBalance);
                                        System.out.printf("Deposit successful! New balance: $%.2f%n", loggedInAccount.getBalance());
                                    } else {
                                        System.out.println("Invalid deposit amount!");
                                    }
                                    break;
                                case 3:
                                    System.out.print("Enter amount to withdraw: $");
                                    double withdrawAmount = scanner.nextDouble();
                                    scanner.nextLine();
                                    if (withdrawAmount > 0 && withdrawAmount <= loggedInAccount.getBalance()) {
                                        double updatedBalance = loggedInAccount.getBalance() - withdrawAmount;
                                        loggedInAccount.setBalance(updatedBalance);
                                        System.out.printf("Withdrawal successful! New balance: $%.2f%n", loggedInAccount.getBalance());
                                    } else {
                                        System.out.println("Invalid withdrawal amount or insufficient funds!");
                                    }
                                    break;
                                case 4:
                                    System.out.println("Logging out...");
                                    loggedInAccount = null; // Resetting the logged-in account
                                    break;
                                default:
                                    System.out.println("Invalid option! Please try again.");
                            }
                            if(option == 4) {
                                break; // Exit the account menu
                            }
                        }

                    }

                    break;

                case 3:
                    System.out.println("Thank you for using ATM. Goodbye!");
                    scanner.close();
                    System.exit(0); // Exiting the program

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        }
    }
}
