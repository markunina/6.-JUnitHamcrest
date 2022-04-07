package Accounts;

import Accounts.Account;

public class CheckingAccount extends Account {

    private int balance;

    public CheckingAccount(int balance) {
        super(balance);
    }

    @Override
    public void setBalance(int balance) {
        if (balance >= 0 ){
            super.balance = balance;
        } else {
            super.balance = 0;
        }
    }

    @Override
    public boolean pay(int amount) {
        if(getBalance() >= amount){
            setBalance(getBalance() - amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean transfer(Account account, int amount) {
        if (getBalance() >= amount && account.addMoney(amount) == true) {
            setBalance(getBalance() - amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean addMoney(int amount) {
        setBalance(getBalance() + amount);
        return true;
    }
}
