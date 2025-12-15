package thread.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV4 implements BankAccount{
    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV4(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("withdraw start" + getClass().getSimpleName());

        lock.lock(); // RUNNABLE -> WAITING
        try {
            log("[validation start] amount=" + amount + ", balance=" + balance);

            if(amount > balance){
                log("[validation fail] amount=" + amount + ", balance=" + balance);
                return false;
            }

            log("[validation complete] amount=" + amount + ", balance=" + balance);
            sleep(1000); // withdraw time
            balance -= amount;
            log("[withdraw complete] amount=" + amount + ", balance=" + balance);
        } finally {
            lock.unlock();
        }

        log("withdraw end" + getClass().getSimpleName());
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock();
        try{
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
