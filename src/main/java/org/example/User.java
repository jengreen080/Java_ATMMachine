package org.example;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String uuid;
    private byte pinHash[];
    private ArrayList<Account> accounts;

    public User (String firstName, String lastName, String pin, Bank theBank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accounts = new ArrayList<Account>();
        //  get a new uuid for the user
        this.uuid = theBank.getNewUserUUID();
        //  store the pin's MD5 hash, rather than the original value for security reasons
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        // /n indicates a new line
        System.out.printf("New user %s, %s with ID %s created.\n", lastName, firstName, this.uuid);

    }

    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }

    public String getUUID(){
        return this.uuid;
    }

    /**
     * Get the user's first name
     * @return  the user's first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Check whether a given pin matches the true User pin
     * @param aPin  the pin to check
     * @return      whether the pin is valid or not
     */
    public boolean validatePin(String aPin){

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    public void printAccountsSummary() {
        System.out.printf("\n\n%s's accounts summary\n", this.firstName);
        for (int accountIndex = 0; accountIndex < this.accounts.size(); accountIndex++) {
            System.out.printf("%d) %s\n", accountIndex+1, this.accounts.get(accountIndex).getSummaryLine());
        }
        System.out.println();
    }

    /**
     * Get the number of accounts of the user
     * @return the number of accounts
     */
    public int numAccounts() {
        return this.accounts.size();
    }

    /**
     * Print transaction history for a particular account
     * @param acctIdx   the index of the account to use
     */
    public void printAcctTransHistory(int acctIdx) {
        this.accounts.get(acctIdx).printTransHistory();
    }

    /**
     * Get the balance of a particular account
     * @param acctIdx   the index of the account to use
     * @return          the balance of the account
     */
    public double getAcctBalance(int acctIdx) {
        return this.accounts.get(acctIdx).getBalance();
    }

    /**
     * Get the UUID of a particular account
     * @param acctIdx   the index of the account to use
     * @return          the UUID of the account
     */
    public String getAcctUUID(int acctIdx) {
        return this.accounts.get(acctIdx).getUUID();
    }

    /**
     * Add a transaction to a particular account
     * @param acctIdx   the index of the account
     * @param amount    the amount of the transaction
     * @param memo      the memo of the transaction
     */
    public void addAcctTransaction(int acctIdx, double amount, String memo) {
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }
}
