package comparator;

import java.util.Comparator;

/**
 * DATE:27/12/20
 * AUTHOR:Giacomo Maccagni
 * DESCRIPTION:
 * This class is used to compare Car objects by using
 * the name attribute;
 */
public class Comparator_Car_Name implements Comparator<Car> {

    @Override
    public int compare(Car o1, Car o2) {
        return o1.getName().compareTo(o2.getName());
    }

    @Override
    public Comparator<Car> reversed() {
        return null;
    }

    @Override
    public Comparator<Car> thenComparing(Comparator<? super Car> other) {
        return null;
    }
}//end of class Comparator_Car_Name

