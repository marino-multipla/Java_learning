package comparator;

import comparator.Comparator_Car_Name;
import comparator.Comparator_Car_Serial_Number;
import comparator.Car;

import java.util.ArrayList;
import java.util.Collections;

/**
 * DATE:27/12/20
 * AUTHOR:Giacomo Maccagni
 * DESCRIPTION:
 * This class is used to run Tests;
 *
 */
public class Test_Comparator {

    public static void main(String args[]){

        cars_comparation();

    }//end of method main

    private static void cars_comparation(){

        System.out.println("Test cars_comparation");

        ArrayList<Car> list = new ArrayList<Car>();
        list.add(new Car("D", 10));
        list.add(new Car("B", 2));
        list.add(new Car("A", 3));
        list.add(new Car("A", 4));

        System.out.println("Unordered list"+list);

        Collections.sort(list, new Comparator_Car_Serial_Number());
        System.out.println("Ordered list by serial_number "+list);

        Collections.sort(list, new Comparator_Car_Name());
        System.out.println("Ordered list by name "+list);

        Collections.sort(list, (Car a,Car b) ->{return  Integer.compare(a.getSerial_number(), b.getSerial_number());});
        System.out.println("Ordered list by serial_number using lambda function"+list);

    }//end of method cars_comparation

}//end of class Test
