package generics;

import java.util.ArrayList;
import java.util.List;

/**
 * DATE:05/01/21
 * AUTHOR:Giacomo Maccagni
 * DESCRIPTION:
 * This class is used to test Generics;
 * Example and explanations from https://www.baeldung.com/java-generics
 */
public class Test_Generics {

    public static void main(String args[]){

        test_generic_method();
        //test_wildcards();

    }//end of method main

    /**
     * 2. The Need for Generics
     */
    private static void method_casting(){

        System.out.println("Execute method_casting");

        Integer i = null;

        //CASE 1
        List list_1 = new ArrayList<>();
        list_1.add(1);
        //Here i need to cast the returned value
        //The cast is cluttering our code
        //It is better to express the intention of using specific types and the compiler can ensure the correctness of such type
        i = (Integer) list_1.iterator().next();

        //CASE 2
        //adding the diamond operator <>
        List<Integer> list_2 = new ArrayList<>();
        list_2.add(1);
        //Here the cast is not required
        //The compiler can enforce the type at compile time.
        /*
        In small programs, this might seem like a trivial addition,
        however, in larger programs,
        this can add significant robustness and makes the program
        easier to read.
         */
        i = list_2.iterator().next();

    }//end of method method_casting

    /**
     * 3. Generic Methods
     */
    private static void test_generic_method(){

        System.out.println("Execute test_generic_method");

        List<String> list_of_string = null;

        Integer[] intArray = {1, 2, 3, 4, 5};
        list_of_string = Generics.fromArrayToList(intArray, Object::toString);
        System.out.println("StringList: "+list_of_string);

        list_of_string = Generics.fromArrayToList(intArray, e -> e.toString());
        System.out.println("StringList: "+list_of_string);

    }//end of method test_generic_method

    /**
     * 4. Using Wildcards With Generics
     */
    private static void test_wildcards(){

        System.out.println("Execute test_wildcards");

        /*
         * It is known that Object is the supertype of all Java classes,
         * however, a collection of Object is not the supertype of any collection.
         */
        //TEST_1
        /*
        Generics.jump_animal_type(new Yellow_Duck());
        Generics.jump_animal_type(new Duck());
        Generics.jump_animal_type(new Animal());
        */
        //END TEST_1

        //EXPECTED RESULT
        /*
        The execution is possible here because Animal the supertype
        of Yellow_Duck and Duck;
         */

        //TEST_2
        /*
        List<Animal> list = new ArrayList<>();
        list.add(new Animal());
        Generics.jump_first_animal_type(list);
        */
        //END TEST_2

        //EXPECTED RESULT
        /*
        The compiler notifies an error here because jump_first_animal_type(..)
        requires only List<Animal>;
        The main idea here is that:
        List<Animal> is not the supertype of List<Yellow_Duck>
         */

        //TEST_3
        /*
        List<Yellow_Duck> list = new ArrayList<>();
        list.add(new Yellow_Duck());
        Generics.jump_first_animal_type_lowerbound(list);
        */
        //END TEST_3

        //EXPECTED RESULT
        /*
        The compiler notifies an error here because jump_first_animal_type_lowerbound(..)
        requires only List<T> where T must be at least a super of Duck;
        The main idea here is that:
        Yellow_Duck is not super of Duck;
         */

        //TEST_4
        /*
        List<Animal> list = new ArrayList<>();
        list.add(new Animal());
        Generics.jump_first_animal_type_upperbound(list);
        */
        //END TEST_4

        //EXPECTED RESULT
        /*
        The compiler notifies an error here because jump_first_animal_type_upperbound(..)
        requires only List<T> where T must extends at least Duck;
        The main idea here is that:
        Animal does not extends Duck;
         */

    }//end of method test_wildcards

    /**
     * 5. Type Erasure
     */
    private static void notes_erasure(){

        /*

        Type erasure removes all type parameters and
        replaces it with their bounds or with Object if the
        type parameter is unbounded.
        Thus the bytecode after compilation contains only normal classes,
        interfaces and methods thus ensuring that no new types are produced.
        Proper casting is applied as well to the Object type at compile time.

        FROM
        public <T> List<T> genericMethod(List<T> list) {
            return list.stream().collect(Collectors.toList());
        }

        TO
        // for illustration
        public List<Object> genericMethod(List<Object> list) {
            return list.stream().collect(Collectors.toList());
        }

        // which in practice results in
        public List withErasure(List list) {
            return list.stream().collect(Collectors.toList());
        }

        List<Object>
        List

        FROM
        If the type is bounded, then the type will be replaced by the bound at compile time:

        public <T extends Building> void genericMethod(T t) {
            ...
        }
        TO
        would change after compilation:

        public void genericMethod(Building t) {
            ...
        }
         */

    }//end of method notes_erasure

    /**
     * 6. Generics and Primitive Data Types
     */
    private static void notes_data_types(){

        /*

            To understand why primitive data types don't work, let's remember that generics
            are a compile-time feature, meaning the type parameter is erased and all
            generic types are implemented as type Object.
            type parameters must be convertible to Object. Since primitive types
            don't extend Object, we can't use them as type parameters.

            So, if we want to create a list which can hold integers, we can use the wrapper:

            List<Integer> list = new ArrayList<>();
            list.add(17);
            int first = list.get(0);

            The compiled code will be the equivalent of:

            List list = new ArrayList<>();
            List<Object> ....
            list.add(Integer.valueOf(17));
            int first = ((Integer) list.get(0)).intValue();-----> int value


         */

    }//end of method notes_erasure

    /**
     * This method is used to test a class that implements Generics
     */
    private static void test_class_that_implements_generics(){

      Analysis_Box_Generics<Integer> analysis_box_generics_int = new Analysis_Box_Generics<Integer>();
      analysis_box_generics_int.getList_of_values().add(1);

      Analysis_Box_Generics<Double> analysis_box_generics_double = new Analysis_Box_Generics<Double>();
      analysis_box_generics_double.getList_of_values().add(1.5);

      Analysis_Box_Generics<String> analysis_box_generics_string = new Analysis_Box_Generics<String>();
      analysis_box_generics_string.getList_of_values().add("hello");

      /*
      Analysis_Box analysis_box = new Analysis_Box();
      analysis_box.getList_of_values().add(1.5);
      analysis_box.getList_of_values().add("asasa");
      */

    }//end of method test_class_that_implements_generics

}//end of class Test_Generics
