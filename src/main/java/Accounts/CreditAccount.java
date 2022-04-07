package Accounts;

import Accounts.Account;

public class CreditAccount extends Account {
    private int balance;

    public CreditAccount(int balance) {
        super(balance);
    }

    @Override
    public void setBalance(int balance) {
        if (balance <= 0 ){
            super.balance = balance;
        } else {
            super.balance = 0;
        }

    }

    @Override
    public boolean pay(int amount) {
        setBalance(getBalance() - amount);
        return true;
    }

    @Override
    public boolean transfer(Account account, int amount) {
        if (account.addMoney(amount)) {
            setBalance(getBalance() - amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean addMoney(int amount) {
        if (getBalance() + amount <= 0) {
            setBalance(getBalance() + amount);
            return true;
        }
        return false;
    }
}
