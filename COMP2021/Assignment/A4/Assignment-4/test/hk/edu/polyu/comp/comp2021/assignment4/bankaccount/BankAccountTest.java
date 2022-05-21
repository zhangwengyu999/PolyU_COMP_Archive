package hk.edu.polyu.comp.comp2021.assignment4.bankaccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

public class BankAccountTest {

    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    public void test() {
        for(int i = 1; i < 10; i++){
            runOnce();
        }
    }

    private void runOnce(){
        BankAccount ba = new BankAccount(0); // initialize the bank account to have balance 0
        Thread withdrawer = new Thread(new Withdrawer(ba, 3));
        Thread depositor = new Thread(new Depositor(ba, 5));
        withdrawer.start();
        depositor.start();
        try {
            withdrawer.join();
            depositor.join();
        }
        catch(InterruptedException ignored){ }

        assertEquals(20, ba.getBalance());
    }

    /** Additional Test */
    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    public void test2() {
        for(int i = 1; i < 10; i++){
            runOnce();
        }
    }

    private void runOnce2(){
        BankAccount ba = new BankAccount(20); // initialize the bank account to have balance 0
        Thread withdrawer = new Thread(new Withdrawer(ba, 4));
        Thread depositor = new Thread(new Depositor(ba, 4));
        withdrawer.start();
        depositor.start();
        try {
            withdrawer.join();
            depositor.join();
        }
        catch(InterruptedException ignored){ }

        assertEquals(20, ba.getBalance());
    }
}


