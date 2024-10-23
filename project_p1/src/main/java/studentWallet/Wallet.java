  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package studentWallet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julia
 */

/**
 * Represents a student's wallet with balances and transaction history.
 */
public class Wallet implements Serializable {
    private double generalBalance; 
    private double printingBalance; 
    private List<String> transactionHistory; // History of transactions

    // constructor to initialize balances and transaction history
    public Wallet() {
        this.generalBalance = 0.0;
        this.printingBalance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public double getGeneralBalance() {
        return generalBalance;
    }

    public double getPrintingBalance() {
        return printingBalance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void topUpGeneralBalance(double amount) {
        generalBalance += amount;
        transactionHistory.add("Topped up general balance by $" + String.format("%.2f", amount));
    }

    public void topUpPrintingBalance(double amount) {
        printingBalance += amount;
        transactionHistory.add("Topped up printing balance by $" + String.format("%.2f", amount));
    }
}
