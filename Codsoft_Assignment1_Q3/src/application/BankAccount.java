package application;
public class BankAccount {
    private String userId;
    private double balance;

    public BankAccount(String userId, double initialBalance) {
        this.userId = userId;
        this.balance = initialBalance;
    }

    public String getUserId() {
        return userId;
    }

    public double getBalance() {
        return balance;
    }

    public double deposit(double amount) {
        balance += amount;
        return balance;
    }

    public double withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return balance;
        } else {
            System.out.println("Insufficient funds");
            return -1;
        }
    }
}
