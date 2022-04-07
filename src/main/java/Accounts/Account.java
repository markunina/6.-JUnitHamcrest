package Accounts;

public abstract class Account {
    protected int balance;

    public Account(int balance) {
        this.setBalance(balance);
    }

    public abstract boolean pay(int amount);

    public abstract boolean transfer(Account account, int amount);

    public abstract boolean addMoney(int amount);

    public int getBalance() {
        return balance;
    }

    public abstract void setBalance(int balance);

    public String getStatus(boolean status) {
        String message;
        if (status) {
            message = "Операция выполнена успешно";
        } else {
            message = "Операция не выполнена";
        }
        return message;
    }

    public boolean amountNotMinus(int amount){
        if (amount < 0){
            return false;
        }
        return true;
    }
}
