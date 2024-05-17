package application;
import java.util.Scanner;

import java.util.HashMap;
import java.util.Map;

public class ATM_Machine {
    private Map<String, BankAccount> accounts = new HashMap<>();

    public void addAccount(BankAccount account) {
        accounts.put(account.getUserId(), account);
    }

    public int deposit(String userId, double amount) {
        BankAccount account = accounts.get(userId);
        if (account != null) {
            if (amount > 0) {
                account.deposit(amount);
                System.out.println("Deposit successful");
                return 1;
            } else {
                System.out.println("Invalid deposit amount");
                return 0;
            }
        } else {
            System.out.println("Account not found");
            return -1;
        }
    }

    public int withdraw(String userId, double amount) {
        BankAccount account = accounts.get(userId);
        if (account != null) {
            if (amount > 0 && account.getBalance() >= amount) {
                account.withdraw(amount);
                System.out.println("Withdraw successful");
                return 1;
            } else {
                System.out.println("Invalid withdraw amount or insufficient funds");
                return 0;
            }
        } else {
            System.out.println("Account not found");
            return -1;
        }
    }

    public double checkBalance(String userId) {
        BankAccount account = accounts.get(userId);
        if (account != null) {
            return account.getBalance();
        } else {
            System.out.println("Account not found");
            return 0;
        }
    }
    public double checkUserId(String userId) {
        BankAccount account = accounts.get(userId);
        if (account != null) {
        	System.out.println("Account  found");
            return 1;
        } else {
            System.out.println("Account not found");
            return 0;
        }
    }
}
