package comparator;

import java.util.Comparator;

/**
 * DATE:27/12/20
 * AUTHOR:Giacomo Maccagni
 * DESCRIPTION:
 * This class is used to compare Car objects by using
 * the serial_number attribute;
 */
public class Comparator_Car_Serial_Number implements Comparator<Car> {

    @Override
    public int compare(Car o1, Car o2) {
        return Integer.compare(o1.getSerial_number(), o2.getSerial_number());
    }

    @Override
    public Comparator<Car> reversed() {
        return null;
    }

    @Override
    public Comparator<Car> thenComparing(Comparator<? super Car> other) {
        return null;
    }

}
