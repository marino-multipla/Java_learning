package streams;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * DATE:11/01/21
 * AUTHOR:Giacomo Maccagni
 * DESCRIPTION:
 * This class is used to test Streams;
 * Example and explanations from https://www.baeldung.com/java-8-streams
 */
public class Test_Streams {

    public static void main(String args[]){

        try {

             analysis_financial_data_using_loops();

             analysis_financial_data_using_streams();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//end of method main

    /**
     * Example of comparing Stream vs Loop;
     */
    private static void example_stream_vs_loop_code() {

        //CREATE list
        List<Person> list_of_person = new ArrayList<Person>();
        list_of_person.add(new Person("A", 16));
        list_of_person.add(new Person("B", 26));
        list_of_person.add(new Person("C", 36));
        list_of_person.add(new Person("D", 6));

        System.out.println("Original list_of_person");
        System.out.println(list_of_person);

        //STREAM usage
        System.out.println("Filtered list_of_person using STREAM");
        System.out.println(list_of_person.stream().filter( p -> p.getAge() < 19).collect(Collectors.toList()));

        //LOOP usage
        List<Person> list_of_person_filtered = new ArrayList<Person>();
        for(Person p : list_of_person) {
            if(p.getAge() < 19) {
                list_of_person_filtered.add(p);
            }
        }
        System.out.println("Filtered list_of_person using LOOPs");
        System.out.println(list_of_person_filtered);

    }//end of method example_stream_vs_loop_code(...)

    /*
     * IMPORTANT REMAINDERS
     *  - it is very important to remember that Java 8 streams can't be reused;
     *  - In a real app, don't leave an instantiated streams unconsumed as that will lead to memory leaks,
     *  add the close(...) method;
     */

    /*
     * It is possible to instantiate a stream and to have an
     * accessible reference to it as long as only intermediate
     * operations were called.
     * Executing a terminal operation makes a stream inaccessible.
     *
     * But an attempt to reuse the same reference after calling
     * the terminal operation will trigger the IllegalStateException;
     *
     * As the IllegalStateException is a RuntimeException,
     * a compiler will not signalize about a problem.
     *
     * So, it is very important to remember that Java 8 streams can't be reused.
     *
     * This kind of behavior is logical because streams were designed
     * to provide an ability to apply a finite sequence of operations
     * to the source of elements in a functional style,
     * but not to store elements.
     */

    /*
     *  Concepts
     *
     *  2. Stream Creation
     *  3. Referencing a Stream
     *  4. Stream Pipeline
     *  5. Lazy Invocation
     *  6. Order of Execution
     *  7. Stream Reduction
     */

    /*
     * Conclusions
     * The Stream API is a powerful but simple to understand set of tools for
     * processing sequence of elements. It allows us to reduce a huge amount of boilerplate code,
     * create more readable programs and improve app’s productivity when used properly.
     * In most of the code samples shown in this article streams
     * were left unconsumed (we didn't apply the close() method or a terminal operation).
     * In a real app, don't leave an instantiated streams unconsumed as that will lead to memory leaks.
     */

    /**
     * 5. Lazy Invocation
     */
    private static void test_lazy_invocation(){

        /*
         * The right and most convenient way to use streams are by a stream pipeline,
         * which is a chain of stream source, intermediate operations,
         * and a terminal operation.
         */

        //TEST_1

        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        list.stream().filter(element -> {
            System.out.println("filter is executed for "+element.toString());
            return element.contains("2");
        });

        //END TEST_1
        /*
         * Here the filter function is NEVER executed so nothing will be printed;
         * The reason why – is missing of the terminal operation.
         * If you add a terminal operation like count(), the filter
         * will be executed three times;
         */

        //TEST_2
        /*
        List<String> list = Arrays.asList("abc2", "abc7", "abc3", "abc2", "abc2");
        Optional<String> stream = list.stream().filter(element -> {
            System.out.println("filter is executed for "+element.toString());
            return element.contains("2");
        }).map(element -> {
            System.out.println("map is executed for "+element.toString());
            return element.toUpperCase();
        }).findFirst();

        System.out.println("stream result "+stream.toString());
        */
        //END TEST_2
        /*
         * Resulting log shows that the filter() method was called twice and the map() method just once.
         * It is so because the pipeline executes vertically.
         * In our example the first element of the stream didn't satisfy filter's predicate,
         * then the filter() method was invoked for the second element, which passed the filter.
         * Without calling the filter() for third element we went down through pipeline to the map() method.
         * The findFirst() operation satisfies by just one element.
         * So, in this particular example the lazy invocation allowed
         * to avoid two method calls – one for the filter() and one for the map().
         */

        //TEST_3
        /*
        List<String> list = Arrays.asList("abc2", "abc7", "abc3", "abc2", "abc9");
        Optional<String> stream = list.stream().filter(element -> {
            System.out.println("filter is executed for "+element.toString());
            return false;
        }).map(element -> {
            System.out.println("map is executed for "+element.toString());
            return element.toUpperCase();
        }).findFirst();
        System.out.println("stream result "+stream.toString());
        */
        //END TEST_3
        /*
         * Here the filter always returns FALSE, so it is executed 5 times;
         * But the filter condition is never satisfied and so no elements will
         * be inside the returned stream.
         * So the map function will be never called;
         */

        //TEST_4
        /*
        List<String> list = Arrays.asList("abc2", "abc7", "abc3", "abc2", "abc9");
        Optional<String> stream = list.stream().filter(element -> {
            System.out.println("filter is executed for "+element.toString());
            return true;
        }).map(element -> {
            System.out.println("map is executed for "+element.toString());
            return element.toUpperCase();
        }).findFirst();
        System.out.println("stream result "+stream.toString());
        */
        //END_TEST_4
        /*
         * Here the filter function is executed one time and returns true;
         * Then the map function will be executed one time;
         */

        //TEST_5
        /*
        List<String> list = Arrays.asList("abc2", "abc7", "abc3", "abc2", "abc9");
       List<String> result_list = list.stream().map(element -> {
            System.out.println("map is executed for "+element.toString());
            return element.substring(0,2);
        }).collect(Collectors.toList());
        System.out.println("stream result "+result_list.toString());
        */
        //END_TEST_5
        /*
         * Here the map function is executed for all elements;
         */

        //TEST_6
        /*
        List<String> list = Arrays.asList("abc2", "abc7", "abc3", "abc2", "abc9");
        List<String> result_list = list.stream().filter(element -> {
            System.out.println("filter is executed for "+element.toString());
            return false;
        }).collect(Collectors.toList());
        System.out.println("stream result "+result_list.toString());
        */
        //END_TEST_6
        /*
         * Here the filter function is always executed;
         */

    }//end of method method_casting

    /**
     * 6. Order of Execution
     */
    private static void test_order_of_execution(){

        /*
         * This brings us up to the rule: intermediate operations which reduce
         * the size of the stream should be placed before operations which are applying to each element.
         * So, keep such methods as skip(), filter(), distinct() at the top of your stream pipeline.
         */

    }//end of method test_order_of_execution(...)

    /**
     * 7. Stream Reduction
     */

    /*
     * The API has many terminal operations which aggregate
     * a stream to a type or to a primitive, for example, count(), max(), min(), sum(),
     * but these operations work according to the predefined implementation.
     * And what if a developer needs to customize a Stream's reduction mechanism?
     * There are two methods which allow to do this – the reduce() and the collect() methods.
     */

    /**
     * 7.1. The reduce() Method
     */
    private static void test_stream_reduction_reduce_method(){

        /*
         * identity – the initial value for an accumulator or
         * a default value if a stream is empty and there is nothing to accumulate;

         * accumulator – a function which specifies a logic of aggregation of elements.
         * As accumulator creates a new value for every step of reducing,
         * the quantity of new values equals to the stream's size and only the last value is useful.
         * This is not very good for the performance.

         * combiner – a function which aggregates results of the accumulator.
         * Combiner is called only in a parallel mode to reduce results
         * of accumulators from different threads.

         */

        OptionalInt reduced =
                IntStream.range(1, 4).reduce((a, b) -> a + b);
        System.out.println("reduced "+reduced);

        int reducedTwoParams =
                IntStream.range(1, 4).reduce(10, (a, b) -> a + b);
        System.out.println("reducedTwoParams "+reducedTwoParams);

        int reducedParams = Stream.of(1, 2, 3)
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                   System.out.println("combiner is called");
                    return a + b;
                });
        System.out.println("reducedParams "+reducedParams);

        int reducedParallel = Arrays.asList(1, 2, 3).parallelStream()
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                    System.out.println("combiner is called");
                    return a + b;
                });
        System.out.println("reducedParallel "+reducedParallel);

    }//end of method test_stream_reduction_reduce_method(...)

    /**
     * 7.2. The collect() Method
     */
    private static void test_stream_reduction_collect_method(){

        /*
         * Reduction of a stream can also be executed by another terminal operation – the collect() method.
         * It accepts an argument of the type Collector, which specifies the mechanism of reduction.
         * There are already created predefined collectors for most common operations.
         * They can be accessed with the help of the Collectors type.
         */

        //In this section we will use the following List as a source for all streams:
        List<Product> list_source = Arrays.asList(new Product(23, "potatoes"),
                new Product(14, "orange"), new Product(13, "lemon"),
                new Product(23, "bread"), new Product(13, "sugar"));
        System.out.println("Original source:");
        System.out.println(list_source);
        System.out.println();

        //Converting a stream<Product> to List<String> using the Product.name
        List<String> list_of_product_name = list_source.stream().map(Product::getName).collect(Collectors.toList());
        System.out.println("list_of_product_name:");
        System.out.println(list_of_product_name);
        System.out.println();

        //Converting a stream<Product> to String by joining Product.name
        String string_of_products = list_source.stream().map(Product::getName)
                .collect(Collectors.joining(", ", "{", "}"));
        System.out.println("string_of_products:");
        System.out.println(string_of_products);
        System.out.println();

        //Processing the average value of all numeric elements of the stream
        double averagePrice = list_source.stream()
                .collect(Collectors.averagingInt(Product::getPrice));
        System.out.println("average_price:");
        System.out.println(averagePrice);
        System.out.println();

        //Processing the sum of all numeric elements of the stream using map:
        int summingPrice = list_source.stream().map(Product::getPrice)
                .collect(Collectors.summingInt(i -> i));
        System.out.println("summingPrice:");
        System.out.println(summingPrice);
        System.out.println();

        //Processing the sum of all numeric elements of the stream using map automatically
        summingPrice = list_source.stream()
                .collect(Collectors.summingInt(Product::getPrice));
        System.out.println("summingPrice:");
        System.out.println(summingPrice);
        System.out.println();

        /*
         * Methods averagingXX(), summingXX() and summarizingXX() can work as with primitives (int, long, double)
         * as with their wrapper classes (Integer, Long, Double).
         * One more powerful feature of these methods is providing the mapping.
         * So, developer doesn't need to use an additional map() operation before the collect() method.
         */

        //Collecting statistical information about stream’s elements:
        IntSummaryStatistics statistics = list_source.stream()
                .collect(Collectors.summarizingInt(Product::getPrice));
        System.out.println("statistics way_1:");
        System.out.println(statistics);
        System.out.println("statistics way_2:");
        System.out.println("MAX:"+statistics.getMax()+" - MIN:"+statistics.getMin());
        System.out.println();

        //Grouping of stream’s elements according to the specified function:
        Map<Integer, List<Product>> collectorMapOfLists = list_source.stream()
                .collect(Collectors.groupingBy(Product::getPrice));
        System.out.println("collectorMapOfLists:");
        System.out.println(collectorMapOfLists);
        System.out.println();

        //In the example above the stream was reduced to the Map which groups all products by their price.

        //Dividing stream’s elements into groups according to some predicate:
        Map<Boolean, List<Product>> mapPartioned = list_source.stream()
                .collect(Collectors.partitioningBy(element -> element.getPrice() > 15));
        System.out.println("mapPartioned:");
        System.out.println(mapPartioned);
        System.out.println();

        /*
         * If for some reason, a custom collector should be created,
         * the most easier and the less verbose way of doing so – is to use the method of() of the type Collector.
         */

        Collector<Product, ?, LinkedList<Product>> toLinkedList =
                Collector.of(LinkedList::new, LinkedList::add,
                        (first, second) -> {
                            first.addAll(second);
                            return first;
                        });

        LinkedList<Product> linkedListOfProducts =
                list_source.stream().collect(toLinkedList);
        System.out.println("linkedListOfProducts:");
        System.out.println(linkedListOfProducts);
        System.out.println();

        /*
         * Before Java 8, parallelization was complex.
         * Emerging of the ExecutorService and the ForkJoin simplified developer’s life a little bit,
         * but they still should keep in mind how to create a specific executor,
         * how to run it and so on. Java 8 introduced a way of accomplishing parallelism in a functional style.
         * The API allows creating parallel streams, which perform operations in a parallel mode.
         * When the source of a stream is a Collection or an array it can be achieved with
         * the help of the parallelStream() method.
         */
        Stream<Product> streamOfCollection = list_source.parallelStream();
        boolean isParallel = streamOfCollection.isParallel();
        boolean bigPrice = streamOfCollection
                .map(product -> product.getPrice() * 12)
                .anyMatch(price -> price > 200);
        System.out.println("streamOfCollection is parallel: "+isParallel);
        System.out.println("streamOfCollection contains a product price > 200: "+bigPrice);

        IntStream intStreamParallel = IntStream.range(1, 150).parallel();
        isParallel = intStreamParallel.isParallel();
        System.out.println("intStreamParallel is parallel: "+isParallel);

        IntStream intStreamSequential = intStreamParallel.sequential();
        isParallel = intStreamSequential.isParallel();
        System.out.println("intStreamSequential is parallel: "+isParallel);

    }//end of method test_stream_reduction_collect_method(...)

    /**
     * Example of Compute execution time
     */
    private static void compute_execution_time() throws InterruptedException {
        Long start_time = System.currentTimeMillis();

        //Some stuff
        Thread.sleep(5000);

        Long stop_time = System.currentTimeMillis();

        Long execution_time = (stop_time - start_time)/1000;

        System.out.println("Execution time is: "+execution_time+" seconds");

    }//end of method compute_execution_time(...)

    /**
     * 2. Stream Creation from FILE
     */
    private static void test_stream_creation() throws IOException {

        /*
         * Java NIO class Files allows to generate a Stream<String> of a text file through the lines() method.
         * Every line of the text becomes an element of the stream.
         */
        Path path = Paths.get("file.txt");
        Stream<String> streamOfStrings = Files.lines(path);
        Stream<String> streamWithCharset =
                Files.lines(path, Charset.forName("UTF-8"));
        List<String> list = streamOfStrings.collect(Collectors.toList());
        System.out.println(list);

    }//end of method test_stream_creation(...)

    /**
     * Example analyze Financial Data using Loops
     */
    private static void analysis_financial_data_using_loops(){

        try{

            System.out.println("Start analysis_financial_data_using_loops");
            Long start_time = System.currentTimeMillis();

            //LOAD dataset
            ArrayList<Stock_Record> dataset = Methods_TXT.load_AL_Stock_Record("", "dataset_big");

            //COMPUTE values
            int size = dataset.size();
            double min_price = 0;
            double max_price = 0;
            double avg_price = 0;

            Stock_Record stock_record = null;

            for(int i=0; i<dataset.size(); i++){

                stock_record = dataset.get(i);

                //compute min
                if(min_price == 0 || stock_record.getPrice() < min_price){
                    min_price = stock_record.getPrice();
                }

                //compute max
                if(max_price == 0 || stock_record.getPrice() > max_price){
                    max_price = stock_record.getPrice();
                }

                //compute avg
                avg_price = avg_price + stock_record.getPrice();

            }//end for cycle over dataset

            avg_price = avg_price / size;

            //PRINT values
            System.out.println("max_price:"+max_price+" min_price:"+min_price+" avg_price:"+avg_price);

            //PRINT execution time
            Long stop_time = System.currentTimeMillis();
            Long execution_time = (stop_time - start_time)/1000;
            System.out.println("Execution time is: "+execution_time+" seconds");
            System.out.println();

        }//end of try block
        catch(Exception e){
            System.err.println("Error during analysis_financial_data_using_loops");
            e.printStackTrace();
        }//end of catch block

    }//end of method analysis_financial_data_using_loops(...)

    private static void analysis_financial_data_using_streams(){
        try{

            System.out.println("Start analysis_financial_data_using_streams");
            Long start_time = System.currentTimeMillis();

            //LOAD dataset
            Path path = Paths.get("dataset_big.txt");
            Stream<String> streamOfStrings = Files.lines(path).parallel();

            //COMPUTE values
            DoubleSummaryStatistics statistics = streamOfStrings
                    .collect(Collectors.summarizingDouble(string -> {
                        try {
                            return Stock_Record.parse(string).getPrice();
                        } catch (Exception e) {
                            e.printStackTrace();
                            return -1;
                        }
                    }));

            System.out.println(statistics);
            streamOfStrings.close();

            //PRINT execution time
            Long stop_time = System.currentTimeMillis();
            Long execution_time = (stop_time - start_time)/1000;
            System.out.println("Execution time is: "+execution_time+" seconds");
            System.out.println();

        }//end try block
        catch(Exception e){
            System.err.println("Error during analysis_financial_data_using_streams");
            e.printStackTrace();
        }//end catch block

    }//end of method analysis_financial_data_using_streams(...)



}//end of class Test_Streams
