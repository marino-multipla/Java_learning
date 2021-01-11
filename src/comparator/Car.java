package comparator;

/**
 * DATE:27/12/20
 * AUTHOR:Giacomo Maccagni
 * DESCRIPTION:
 * This class is used to represent a car;
 */
public class Car {

    /*
     * DECLARE attributes of the class
     */
    private String name;
    private  int serial_number;

    /*
     * DECLARE gets and sets of the class
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(int serial_number) {
        this.serial_number = serial_number;
    }

    /*
     * DECLARE constructors of the class
     */

    public Car() {
        this.name = "";
        this.serial_number = 0;
    }

    public Car(String name, int serial_number) {
        this.name = name;
        this.serial_number = serial_number;
    }

    /*
     * DECLARE methods of the class
     */
    @Override
    public String toString(){
        return this.name+";"+this.serial_number;
    }

}//end of class Car

