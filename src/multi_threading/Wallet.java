package multi_threading;

/**
 * DATE:17/01/21
 * AUTHOR:Giacomo Maccagni
 * DESCRIPTION:
 * This class is used to represent Wallet;
 */
public class Wallet {

    private int number_of_executed_trasnactions;

    public Wallet(){
        this.number_of_executed_trasnactions = 0;
    }

    public int getNumber_of_executed_trasnactions() {
        return number_of_executed_trasnactions;
    }

    public void setNumber_of_executed_trasnactions(int number_of_executed_trasnactions) {
        this.number_of_executed_trasnactions = number_of_executed_trasnactions;
    }

    /*
     * Try to add synchronized in order to avoid concurrency problem
     */
    public void execute_transaction(){
        this.number_of_executed_trasnactions++;
    }

}//end of class Wallet
