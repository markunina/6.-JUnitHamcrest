import Accounts.*;

public class Main {

    public static void main(String[] args) {
        Account[] accounts = {
                new CheckingAccount(1000),
                new SavingsAccount(1000),
                new CreditAccount(-1000)};
        Account account = new SavingsAccount(100);

        for (Account a : accounts) {
            System.out.println(
                    a.getStatus(a.addMoney(1001)) + "; " +
                    a.getStatus(a.pay(1001)) + "; " +
                    a.getStatus(a.transfer(account, 1000))
            );
        }
    }
}
