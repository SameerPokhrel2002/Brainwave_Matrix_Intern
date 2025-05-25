

import java.util.HashMap; // Importing HashMap for storing ATM data

public class ATM {

    private HashMap<String, Account> accounts =new HashMap<>(); // HashMap to store accounts

    public boolean createAccount(String accountNumber, int pin){
        if(accounts.containsKey(accountNumber)) {
            System.out.println("Account already exists!");
            return false; 
        }
        accounts.put(accountNumber, new Account(accountNumber, pin));
        System.out.println("Account created successfully!");
        return true;
            
        }
        public Account authenticateUser(String accountNumber, int pin){
            if(accounts.containsKey(accountNumber)) {
                Account acc = accounts.get(accountNumber);
                if(acc.getPin() == pin) {
                    return acc; // Returning the authenticated account
                } 
            } 
            System.out.println("Invalid account number or PIN!");
            return null; // Returning null if authentication fails
        }

    }

 

