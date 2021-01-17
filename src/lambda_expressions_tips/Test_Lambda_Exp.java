package lambda_expressions_tips;

import comparator.Car;
import generics.Animal;

import java.util.function.Function;

/**
 * DATE:08/01/21
 * AUTHOR:Giacomo Maccagni
 * DESCRIPTION:
 * This class is used to test Lambda Expressions;
 * Example and explanations from https://www.baeldung.com/java-8-lambda-expressions-tips
 */
public class Test_Lambda_Exp {

    public static void main(String args[]){

        test_interfaces();

        //test_scope();

    }//end of method main

    /**
     * 2. Prefer Standard Functional Interfaces
     */
    private static void test_interfaces(){

        String result = null;

        //TEST_1
        //Here the Interface Foo is instantiated and defined using a lambda function;
        Foo_2 foo = (parameter1, parameter2) -> (parameter1 +" "+ parameter2) + " from lambda marinoooo";
        //result = add("Message1 ", "Message2", foo);
        result = foo.method("Message_1", "Message_2");
        System.out.println(result);
        //END TEST_1

        //TEST_2
        //Here a functional interface Function<String, String> is instantiated and defined using a lambda function;
        //Function<T,R> is an interface where T is the type and R is the result
        Function<String, String> fn =
                parameter -> parameter + " from lambda";
        result = add("Message", fn);
        System.out.println(result);
        //END TEST_2

        //TEST_3
        //Here the Interface Foo is instantiated and defined using a lambda function;
        //Function<T,R> is an interface where T is the type and R is the result
        Foo_3 foo_3 = (p1, p2) -> String.valueOf((p1+p2));
        result = foo_3.method(1,2);
        System.out.println(result);
        //END TEST_3

    }//end of method test_interfaces(...)

    /*
     * Conceptually, a functional interface has exactly one abstract method.
     */
    @FunctionalInterface
    public interface Foo_2 {
        public String method(String parameter1, String parameter2);
    }

    /*
     * Conceptually, a functional interface has exactly one abstract method.
     */
    @FunctionalInterface
    public interface Foo_3 {
        public String method(int parameter1, int parameter2);
    }

    /*
     * Conceptually, a functional interface has exactly one abstract method.
     */
    @FunctionalInterface
    public interface Foo_1 {
        public String method(String parameter1);
    }

    public static String add(String parameter1, String parameter2, Foo_2 foo) {
        return foo.method(parameter1,parameter2);
    }

    public static String add(String parameter1, Function<String, String> fn) {
        /*
         * java.util.function.Function<T, R>
         * public abstract R apply(T t)
         */
        return fn.apply(parameter1);
    }

    /**
     * 3. Use the @FunctionalInterface Annotation
     */
    private static void functional_interface_annotation(){

        /*
         * Annotate your functional interfaces with @FunctionalInterface.
         * At first, this annotation seems to be useless.
         * Even without it, your interface will be treated as functional as long as it has just one abstract method.
         * But imagine a big project with several interfaces – it's hard to control everything manually.
         * An interface, which was designed to be functional,
         * could accidentally be changed by adding of other abstract method/methods,
         * rendering it unusable as a functional interface.
         * But using the @FunctionalInterface annotation,
         * the compiler will trigger an error in response to any attempt
         * to break the predefined structure of a functional interface.
         * It is also a very handy tool to make your application architecture easier to understand for other developers.
         */

    }//end of method functional_interface_annotation(...)

    /**
     * 7. Don’t Treat Lambda Expressions as Inner Classes
     */
    private static void test_scope(){

        /*
         * INNER CLASS
         * When you use an inner class, it creates a new scope.
         * So the variables declared outside the methods will be overwritten during
         * the execution of the inner class.
         * You can hide local variables from the enclosing scope
         * by instantiating new local variables with the same names.
         * You can also use the keyword this inside your inner class as a reference to its instance.
         *
         * LAMBDA FUNCTION
         * However, lambda expressions work with enclosing scope.
         * You can’t hide variables from the enclosing scope inside the lambda’s body.
         * In this case, the keyword this is a reference to an enclosing instance.
         */

        Test_Lambda_Exp t = new Test_Lambda_Exp();
        System.out.println(t.scopeExperiment());

    }//end of method functional_interface_annotation(...)

    private static String value = "Enclosing scope value";

    public String scopeExperiment() {

        Foo_1 foo = parameter -> parameter + " from Foo";

        Foo_1 fooIC = new Foo_1() {
            String value = "Inner class value";

            @Override
            public String method(String string) {
                return this.value;
            }
        };
        String resultIC = fooIC.method("");

        Foo_1 fooLambda = parameter -> {
            String value = "Lambda value";
            return this.value;
        };
        String resultLambda = fooLambda.method("");

        return "Results: resultIC = " + resultIC +
                ", resultLambda = " + resultLambda;
    }

    /**
     * 8. Keep Lambda Expressions Short and Self-explanatory
     */
    private static void test_lambda_expressions_tips(){

     /*
      *  a -> a.toLowerCase();
      *
      *   (a) -> {return a.toLowerCase()};
      *
      *  public String method_1(String element){
      *     return element.toLowerCase();
      *  }
      *
      *  String::toLowerCase;
      */

        //TEST_1
        /*
        Function<String, String> fn = null;
        String result = null;

        //WAY_1
        fn = parameter -> parameter.toLowerCase();
        result = fn.apply("MARINOOOOO");
        System.out.println("Result lambda expression: "+result);

        //WAY_2
        fn = String::toLowerCase;
        result = fn.apply("MARINOOOOOO");
        System.out.println("Result method references: "+result);
         */
        //END_TEST_1

        //TEST_2
        Function<Animal, String> fn = null;
        //fn = Animal::jump_lambda;
        fn = (animal) -> {
            return animal.jump_lambda();
        };

        System.out.println(fn.apply(new Animal()));
        //END_TEST_2

    }//end of method test_lambda_expressions_tips(...)



}//end of class Test_Lambda_Exp

