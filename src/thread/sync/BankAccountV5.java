package thread.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV5 implements BankAccount{
    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV5(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("withdraw start" + getClass().getSimpleName());

        if(!lock.tryLock()){
            log("lock fail");
            return false;
        }


        try{
            log("[validation start] amount=" + amount + ", balance=" + balance);

            if(amount > balance){
                log("[validation fail] amount=" + amount + ", balance=" + balance);
                return false;
            }

            log("[validation complete] amount=" + amount + ", balance=" + balance);
            sleep(1000); // withdraw time
            balance -= amount;
            log("[withdraw complete] amount=" + amount + ", balance=" + balance);

            log("withdraw end" + getClass().getSimpleName());
            return true;
        }finally {
            lock.unlock();
        }

    }

    @Override
    public int getBalance() {
        if(!lock.tryLock()){
            log("lock fail");
        }

        try{
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
