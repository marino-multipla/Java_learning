package multi_threading;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * DATE:17/01/21
 * AUTHOR:Giacomo Maccagni
 * DESCRIPTION:
 * This class is used to test Multi Threading;
 */
public class Test_Multi_Threading {

    public static void main(String args[]){

        //test_concurrency();

        //test_interrupt();

        test_thread_pool();

    }//end of method main

    /**
     * This is an example of Concurrency
     */
    private static void test_concurrency(){

        Wallet wallet = new Wallet();

        Thread thread = null;
        Financial_Operator financial_operator = null;

        for(int i=0; i< 100; i++){

            financial_operator = new Financial_Operator((wallet));
            thread = new Thread(financial_operator);
            thread.start();

        }//end for cycle over i

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*
         * The humber of executed transaction must be 1000 operations executed by 100 financial_operator,
         * so 100000 operations;
         */
        System.out.println("Number of executed transactions "+wallet.getNumber_of_executed_trasnactions());

    }//end of method test_concurrency(...)

    /**
     * This is an example of sending an interrupt
     */
    private static void test_interrupt(){

        //DECLARE variables
        Thread thread = null;
        Musician musician = null;

        ArrayList<Thread> list_of_thread = new ArrayList<Thread>();

        //START the orchestrator
        System.out.println("The orchestrator has started");

        //START the orchestra
        for(int i=0; i<10; i++){

            musician = new Musician("B"+i);
            thread = new Thread(musician);
            list_of_thread.add(thread);
            thread.start();

        }//end for cycle over i

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //TERMINATE all musician
        /*
        for(int i=0; i<list_of_thread.size(); i++){

            list_of_thread.get(i).interrupt();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }//end for cycle over list_of_thread
         */

        //WAIT all musician before quit
        for(int i=0; i<list_of_thread.size(); i++){

            thread = list_of_thread.get(i);

            try {
                thread.wait();
                System.out.println("Musician "+ thread.getId()+" has terminated with state "+thread.getState());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }//end for cycle over list_of_thread

    }//end of method test_interrupt(...)

    private static void test_thread_pool(){

        //DECLARE variables
        ExecutorService executor = null;

        //INITIALIZE thread pool
        executor = Executors.newFixedThreadPool(4);

        for(int i=0; i<10; i++){
            //assign thread
            executor.execute(new Musician("A"+i));
        }//end for cycle over i

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //wait threads
        executor.shutdown();
        while (executor.isTerminated() == false){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }




    }//end of method test_thread_pool(...)

}//end of class Test_Multi_Threading
