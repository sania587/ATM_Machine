package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ATM_Controller {
    
    @FXML
    private TextField userIdField;
    @FXML
    private TextField amountField;
    @FXML
    private Label messageLabel;
    @FXML
    private Button withdrawButton;
    @FXML
    private Button depositButton;
    @FXML
    private Button checkBalanceButton;
    @FXML
    private Button okButton;
    @FXML
    private Button[] numberButtons;

    private ATM_Machine atm;
    private StringBuilder amountBuilder = new StringBuilder();
    private String operation = "";
    private boolean userIdEntered = false;
    @FXML
    private Button numberButton1;
    @FXML
    private Button numberButton2;
    @FXML
    private Button numberButton3;
    @FXML
    private Button numberButton4;
    @FXML
    private Button numberButton5;
    @FXML
    private Button numberButton6;
    @FXML
    private Button numberButton7;
    @FXML
    private Button numberButton8;
    @FXML
    private Button numberButton9;
    @FXML
    private Button numberButton0;
    @FXML
    public void initialize() {
        atm = new ATM_Machine();
        // Adding some sample accounts
        atm.addAccount(new BankAccount("1234", 1000));
        atm.addAccount(new BankAccount("1235", 500));
        
        numberButtons = new Button[]{
            numberButton1, numberButton2, numberButton3, numberButton4, numberButton5,
            numberButton6, numberButton7, numberButton8, numberButton9, numberButton0
        };
        
        disableNumberButtons();
        disableButtons();
        amountField.setEditable(false);
    }


    @FXML
    public void handleNumber(javafx.event.ActionEvent event) {
        if (userIdEntered) {
            Button button = (Button) event.getSource();
            amountBuilder.append(button.getText());
            amountField.setText(amountBuilder.toString());
        }
    }

    @FXML
    public void handleWithdraw() {
      
    	
        operation = "withdraw";
       
        //messageLabel.setText("Withdraw successful. New Balance: $" + atm.checkBalance(Integer.parseInt(userId)));
       
        String userId = userIdField.getText();
        String balance = amountField.getText();
        int check= atm.withdraw(userId, Double.parseDouble(balance));
        if(check==1)
        {
        	messageLabel.setText("Withdraw successful. New Balance: $" + atm.checkBalance(userId));
        }
        else if(check==0)
        {
        	messageLabel.setText("Invalid withdraw amount or insufficient funds");
        }
        else
        {
        	messageLabel.setText("Account not found");
        }
        disableNumberButtons();
        disableButtons();
        okButton.setDisable(false);
        amountField.setEditable(false);
      //  messageLabel.setText("Enter amount to withdraw and press OK");
    }

    @FXML
    public void handleDeposit() {
       // enableAmountFieldAndOkButton("Deposit");
        operation = "deposit";
        String userId = userIdField.getText();
        String balance = amountField.getText();
        int check= atm.deposit(userId, Double.parseDouble(balance));
        if(check==1)
        {
        	messageLabel.setText("Deposit successful. New Balance: $" + atm.checkBalance(userId));
        }
        else if(check==0)
        {
        	messageLabel.setText("Invalid deposit amount");
        }
        else
        {
        	messageLabel.setText("Account not found");
        }
        
        disableNumberButtons();
        disableButtons();
        amountField.setEditable(false);
        okButton.setDisable(false);
       // messageLabel.setText("Enter amount to deposit and press OK");
    }

    @FXML
    public void handleCheckBalance() {
        String userId = userIdField.getText();
        double balance = atm.checkBalance(userId);
        if (balance >= 0) {
            messageLabel.setText("Current Balance: $" + balance);
        } else {
            messageLabel.setText("Account not found");
        }
    }

    @FXML
    public void handleOk() {
        try {
            String userId = userIdField.getText();
            double check=atm.checkUserId(userId);
            if (check == 1) {
            	okButton.setDisable(true);
                messageLabel.setText("Account found");
                withdrawButton.setDisable(false);
                checkBalanceButton.setDisable(false);
                depositButton.setDisable(false);
                for (Button button : numberButtons) {
                    button.setDisable(false);
                }
               // amountField.setEditable(true);
                //amountField.setEditable(true);
                userIdEntered = true;
            } else {
                messageLabel.setText("Account not found");
            }
          
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid input. Please enter a valid amount.");
        }
       /* amountBuilder.setLength(0);
        amountField.clear();
        operation = "";
        disableNumberButtons();
        disableButtons();*/
    }

    private void disableNumberButtons() {
        for (Button button : numberButtons) {
            button.setDisable(true);
        }
    }
    private void disableButtons() {
        if (withdrawButton != null) { // Check if okButton is not null before accessing it
        	withdrawButton.setDisable(true);
        }
        if (checkBalanceButton != null) { // Check if okButton is not null before accessing it
        	checkBalanceButton.setDisable(true);
        }
       
        if (depositButton != null) { // Check if okButton is not null before accessing it
        	depositButton.setDisable(true);
        }   
    }

    private void enableAmountFieldAndOkButton(String action) {
        userIdEntered = true;
        amountField.setEditable(true);
       // okButton.setDisable(false);
       // messageLabel.setText("Enter User ID and press OK");
    }
}
