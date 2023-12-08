package org.example;
import java.util.Date;

public class Transaction {
    private double amount;
    private Date timestamp;
    private String memo;
    private Account inAccount;

    /**
     * Create anew transaction
     * @param amount        the amount transacted
     * @param inAccount     the account the transaction belongs to
     */
    public Transaction(double amount, Account inAccount){
        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo = "";
    }

    /**
     * Create a new transaction
     * @param amount    the amount transacted
     * @param memo      the memo for the transaction
     * @param inAccount the account the transaction belongs to
     */
    public Transaction(double amount, String memo, Account inAccount){
//        call the two argument constructor first
        this(amount, inAccount);
//        set the memo
        this.memo = memo;
    }

    /**
     * Get the amount of the transaction
     * @return the amount
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Get a string summarising the transaction
     * @return the  summary string
     */
    public String getSummaryLine() {
        if (this.amount >= 0) {
            return String.format("%s : £%.2f : %s\n", this.timestamp.toString(), this.amount, this.memo);
        } else {
            return String.format("%s : £(%.2f) : %s\n", this.timestamp.toString(), -this.amount, this.memo);
        }
    }

}
