package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV3 implements BankAccount{
    private int balance;

    public BankAccountV3(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("withdraw start" + getClass().getSimpleName());

        synchronized (this){
            log("[validation start] amount=" + amount + ", balance=" + balance);

            if(amount > balance){
                log("[validation fail] amount=" + amount + ", balance=" + balance);
                return false;
            }

            log("[validation complete] amount=" + amount + ", balance=" + balance);
            sleep(1000); // withdraw time
            balance -= amount;
            log("[withdraw complete] amount=" + amount + ", balance=" + balance);
        }

        log("withdraw end" + getClass().getSimpleName());
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }
}
