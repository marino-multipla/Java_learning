package multi_threading;

/**
 * DATE:17/01/21
 * AUTHOR:Giacomo Maccagni
 * DESCRIPTION:
 * This class is used to represent a
 * Musician of an Orchestra;
 */
public class Musician implements  Runnable{

    private String name;
    private int number_of_gingle_repetititon;

    public Musician(){

    }

    public Musician(String name){
        this.name = name;
    }


    @Override
    public void run() {

        try {

            this.number_of_gingle_repetititon = 0;

            while(this.number_of_gingle_repetititon < 20){

                this.number_of_gingle_repetititon++;
                Thread.sleep(3000);
                System.out.println("Musician "+this.name+" is active with thread "+Thread.currentThread().getId());
            }
        }catch (InterruptedException e){
            System.out.println("Musician "+this.name+" is interrupted");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("Musician "+this.name+" is stopped");
        }

    }
}//end of class Musician
