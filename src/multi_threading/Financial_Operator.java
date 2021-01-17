package multi_threading;

/**
 * DATE:17/01/21
 * AUTHOR:Giacomo Maccagni
 * DESCRIPTION:
 * This class is used to represent a Financial operator that executes transactions using wallet;
 */
public class Financial_Operator implements Runnable{

    private Wallet wallet;

    public Financial_Operator(Wallet wallet){
        this.wallet = wallet;
    }

    @Override
    public void run() {

        for(int i=0; i<1000; i++){
            wallet.execute_transaction();
            System.out.println("Transaction executed for Financial_Operator "+Thread.currentThread().getId());
        }//end for cycle over i

    }
}//end of class Financial_Operator
