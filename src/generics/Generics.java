package generics;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * DATE:05/01/21
 * AUTHOR:Giacomo Maccagni
 * DESCRIPTION:
 * This class is used to implement
 * some generics methods;
 */
public class Generics {

    public <T> List<T> fromArrayToList(T[] a) {

        return Arrays.stream(a).collect(Collectors.toList());
    }

    public <T> void method_1(T element){
        System.out.println(element.toString());
    }

    /**
     *
     * T stands for Type
     * R stands for Result
     */
    public static <T, R> List<R> fromArrayToList(T[] a, Function<T, R> mapperFunction) {
        /*
        return Arrays.stream(a)
                .map(mapperFunction)
                .collect(Collectors.toList());
         */

        Stream<T> stream_T = Arrays.stream(a);
        Stream<R> stream_R = stream_T.map(mapperFunction);
        List<R> list_to_return = stream_R.collect(Collectors.toList());

        return  list_to_return;

    }//end of method fromArrayToList(...)



    /**
     * This method is used to call the
     * jump method of Animal;
     */
    public static void jump_animal_type(Animal animal) {
        animal.jump();
    }//end of method jump_animal_type(...)

    /**
     * Here only Classes that are equal to Animal are admitted inside
     * list;
     */
    public static void jump_first_animal_type(List<Animal> list) {
        list.get(0).jump();
    }//end of method jump_first_animal_type(...)

    /**
     * Here only Classes that extends Duck are admitted inside
     * list; So only child classes of Duck;
     */
    public static void jump_first_animal_type_upperbound(List< ? extends Duck> list) {
        list.get(0).jump();
    }//end of method jump_first_animal_type_upperbound(...)

    /**
     * Here only Classes that are super of Duck are admitted inside
     * list; So only parents classes of Duck;
     *
     * In this case a List<Yellow_Duck> is not admitted because,
     * Yellow_Duck is not super of Duck;
     */
    public static void jump_first_animal_type_lowerbound(List<? super Duck> list) {
        ((Animal)list.get(0)).jump();
    }//end of method jump_first_animal_type_lowerbound(...)

}//end of class Generics
