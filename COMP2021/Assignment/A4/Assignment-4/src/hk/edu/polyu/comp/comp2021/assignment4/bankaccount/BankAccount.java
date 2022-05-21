package hk.edu.polyu.comp.comp2021.assignment4.bankaccount;

/** A bank account object. */
public class BankAccount { 

    private int balance;

	/** Instantiate an account with 'initialBalance'. */
    public BankAccount(int initialBalance){
        if(initialBalance < 0)
            throw new IllegalArgumentException();

        balance = initialBalance;
    }

	/** Balance of the account. The balance should never be negative. */
    public int getBalance(){
        return balance;
    }

	/** Deposit 'amount' into this account. 'amount' should always be positive. */
    public void deposit(int amount){
        if(amount <= 0)
            throw new IllegalArgumentException();

        // Add missing code here
        // ZHANG Wengyu 21098431d
        try {
            synchronized (this) {
                while (this.getBalance() < 0) // when the banlance is 0, no need to wait, add the amount into the banlance
                    this.wait();
                balance = balance + amount;
                this.notifyAll();
            }
        }
        catch (InterruptedException e) {e.getMessage();}
    }

	/** Withdraw 'amount' from this account. 'amount' should always be positive. */
    public void withdraw(int amount){
        if(amount <= 0)
            throw new IllegalArgumentException();

        // Add missing code here
        try {
            synchronized (this) {
                while (this.getBalance() <= 0) // when the banlance is 0, need to wait
                    this.wait();
                balance = balance - amount;
                this.notifyAll();
            }
        }
        catch (InterruptedException e) {e.getMessage();}

    } // ZHANG Wengyu 21098431d
}
