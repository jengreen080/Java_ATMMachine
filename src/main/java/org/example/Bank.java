package org.example;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    /**
     * Create a new Bank object with empty lists of users and accounts
     * @param name  the name of the bank
     */
    public Bank(String name){
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    /**
     * Get the name of the bank
     * @return  the name of the bank
     */
    public String getName(){
        return this.name;
    }
    public String getNewUserUUID(){
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        //loop until we get a unique ID
        do {
            uuid = "";
            for (int i = 0; i < len; i++){
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            //check to make sure it's unique
            nonUnique = false;
            for (User u : this.users){
                if (uuid.compareTo(u.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }

        } while(nonUnique);

        return uuid;
    }

    public String getNewAccountUUID(){
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;

        //loop until we get a unique ID
        do {
            uuid = "";
            for (int i = 0; i < len; i++){
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            //check to make sure it's unique
            nonUnique = false;
            for (Account a : this.accounts){
                if (uuid.compareTo(a.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }

        } while(nonUnique);

        return uuid;
    }

    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }

    public User addUser(String firstName, String lastName, String pin){

        //create a new User object and add it to our list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        //create a savings account for the user
        Account newAccount = new Account("Savings", newUser, this);
        //add account to holder and bank lists
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;
    }

    public User userLogin(String userID, String pin){
        //search through list of users
        for (User u: this.users){
            //check user ID is correct
            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                return u;
            }
        }
        //if we haven't found user or have an incorrect pin
        return null;
    }
}

