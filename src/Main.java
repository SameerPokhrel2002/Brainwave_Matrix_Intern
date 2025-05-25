import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);
        atm.loadAccountsFromFile(); // Load existing accounts from file

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
                    scanner.nextLine();

                    //pin validation before creating account
                    if(pin < 1000 || pin > 9999) {
                        System.out.println("Invalid PIN! Please enter a 4-digit PIN.");
                        
                        break;
                    }
                    
                    
                    boolean created=atm.createAccount(accNum, pin);
                    if(created){
                        atm.saveAccountsToFile();
                    }
                    break;

                case 2: // Login
                    System.out.print("Enter account number: ");
                    String loginAccNum = scanner.nextLine();
                    System.out.print("Enter PIN: ");
                    int loginPin = scanner.nextInt();
                    scanner.nextLine();
                    Account loggedInAccount = atm.authenticateUser(loginAccNum, loginPin);
                    if (loggedInAccount != null) {
                        clearConsole(); // Clear console after successful login
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
                                clearConsole();
                                    System.out.printf("Your current balance is: $%.2f%n", loggedInAccount.getBalance());
                                    break;
                                case 2:
                                clearConsole();
                                    System.out.print("Enter amount to deposit: $");
                                    double depositAmount = scanner.nextDouble();
                                    scanner.nextLine();
                                    if (depositAmount > 0) {
                                        double newBalance = loggedInAccount.getBalance() + depositAmount;
                                        loggedInAccount.setBalance(newBalance);
                                        atm.saveAccountsToFile(); // Saving after deposit
                                        System.out.printf("Deposit successful! New balance: $%.2f%n",
                                                loggedInAccount.getBalance());
                                    } else {
                                        System.out.println("Invalid deposit amount!");
                                    }
                                    break;
                                case 3:
                                clearConsole();
                                    System.out.print("Enter amount to withdraw: $");
                                    double withdrawAmount = scanner.nextDouble();
                                    scanner.nextLine();
                                    if (withdrawAmount > 0 && withdrawAmount <= loggedInAccount.getBalance()) {
                                        double updatedBalance = loggedInAccount.getBalance() - withdrawAmount;
                                        loggedInAccount.setBalance(updatedBalance);
                                        atm.saveAccountsToFile(); // Saving after withdrawal
                                        System.out.printf("Withdrawal successful! New balance: $%.2f%n",
                                                loggedInAccount.getBalance());
                                    } else {
                                        System.out.println("Invalid withdrawal amount or insufficient funds!");
                                    }
                                    break;
                                case 4:
                                    System.out.println("Logging out...");
                                    try{
                                        Thread.sleep(2000); // Adding a delay for better user experience
                                    }catch(InterruptedException e)
                                    {
                                    Thread.currentThread().interrupt(); // Resetting the interrupt status
                                    }
                                    loggedInAccount = null; // Resetting the logged-in account
                                    break;
                                default:
                                    System.out.println("Invalid option! Please try again.");
                            }
                            if (option == 4) {
                                break; // Exit the account menu
                            }
                        }

                    }
                    else{
                        System.out.println("Login failed! Please check your account number and PIN.");
                        try{
                            Thread.sleep(3000); // Adding a delay for better user experience
                        }catch(InterruptedException e)
                        {
                            Thread.currentThread().interrupt(); // Resetting the interrupt status
                        }
                    }

                    break;

                case 3: // Exit
                    System.out.println("Thank you for using ATM. Goodbye!");
                    scanner.close();
                    System.exit(0); // Exiting the program

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        }
    }
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
}
