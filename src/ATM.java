
import java.util.HashMap; // Importing HashMap for storing ATM data
import java.io.*; //for file operations

public class ATM {

    private HashMap<String, Account> accounts = new HashMap<>(); // HashMap to store accounts

    private final String FILE_NAME = "accounts.txt"; // file to store account data

    public boolean createAccount(String accountNumber, int pin) {
        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account already exists!");
            return false;
        }
        accounts.put(accountNumber, new Account(accountNumber, pin));
       
        System.out.println("Account created successfully!");
        return true;

    }

    public Account authenticateUser(String accountNumber, int pin) {
        if (accounts.containsKey(accountNumber)) {
            Account acc = accounts.get(accountNumber);
            if (acc.getPin() == pin) {
                return acc; // Returning the authenticated account
            }
        }
        System.out.println("Invalid account number or PIN!");
        return null; // Returning null if authentication fails
    }

    // load accounts from file
    public void loadAccountsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String accountNumber = parts[0];
                    int pin = Integer.parseInt(parts[1]);
                    double balance = Double.parseDouble(parts[2]);
                    Account acc = new Account(accountNumber, pin);
                    acc.setBalance(balance); //
                    accounts.put(accountNumber, acc); // Adding account to the HashMap
                }
            }
            System.out.println("Accounts loaded from file successfully!");
        } catch (IOException e) {
            System.out.println("No saved account data found or file error. " + e.getMessage());
        }
    }

    // save accounts to file
    public void saveAccountsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Account acc : accounts.values()) {
                writer.println(acc.getAccountNumber() + "," + acc.getPin() + "," + acc.getBalance());
            }
            System.out.println("Account saved to file successfully!");
        } catch (IOException e) {
            System.out.println("Error saving account to file: " + e.getMessage());
        }
    }

}
