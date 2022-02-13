package edu.neu.coe.info6205.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import edu.neu.coe.info6205.sort.*;
import edu.neu.coe.info6205.util.*;

public class BenchMarkPartThree {

    final static LazyLogger logger = new LazyLogger(BenchMarkPartThree.class);

    public static final int N = 2;

    private static Config config;
    
    private int[] array ;
    /**
     * Construct a new Timer and set it running.
     */
    public BenchMarkPartThree(int[] array) {
        this.array = this.createOrder(array);
    
    }
    
    @BeforeClass
    public static void setupClass() {
        try {
            config = Config.load(BenchMarkPartThree.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public static void testInsertionSortBenchmark(List<Integer> list) {
    	Integer[] xs = list.toArray(new Integer[0]);
        String description = "Insertion sort";
        Helper<Integer> helper = new BaseHelper<>(description, N, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        runBenchmark(description, sort, helper, xs);
    }

    
    private int[] createPartiallyOrdered(int[] array) {
	    int index, temp;
	    Random random = new Random();
	    for (int i = array.length/2 ; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        temp = array[index];
	        array[index] = array[i];
	        array[i] = temp;
	    }
		
	    return array;
	}


	private int[] createOrder(int[] array) {
    	int[] num = new int[array.length];
    	int index = 0 ;
        // sorting array
        Arrays.sort(array);
        for (int number : array) {
           num[index] = number ;
           index+=1;
        }
    	
    	return num;
	}
    
	private int[] createRandom(int[] array) {
	    int index, temp;
	    Random random = new Random();
	    for (int i = array.length - 1; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        temp = array[index];
	        array[index] = array[i];
	        array[i] = temp;
	    }
		
	    return array;
	    //System.out.println(Arrays.toString(this.random));
	    
	}


    private int[] createReverseOrder(int[] ordered2) {
    	int[] num = new int[ordered2.length];
    	int index = 0 ;
    	for (int i = ordered2.length-1; i >= 0; i--) {
    	    num[index] = ordered2[i];
    	    index+=1;
    	}
    	
    	return num;
	}


    private int[] getArray() {
        return this.array;
    }

    
    private static void sortArray(List<Integer> list) {
        Integer[] xs = list.toArray(new Integer[0]);
        final Config config = ConfigTest.setupConfig("true", "0", "1", "", "");
        Helper<Integer> helper = HelperFactory.create("InsertionSort", list.size(), config);
        helper.init(list.size());
        SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
        sorter.preProcess(xs);
        Integer[] ys = sorter.sort(xs);
    }
    
    public static double getTimer(int times, List<Integer> list) {

    	//System.out.println(Arrays.toString(list.toArray()));
        final Timer timer = new Timer();
        final double mean = timer.repeat(times, () ->  list, t -> {
        	sortArray(t);
            return null;
        });
        
        return mean;
    }


    
    public static void main(String[] args) {
    	// System.out.println("MileSecond: " + String.valueOf(getClock()));
    	int totalNumber = 100;
    	int[] intArray = new int[totalNumber]; 
    	int times = 100 ;
    	
    	
    	for (int i = 0; i < totalNumber; i++) {
    		intArray[i] = i+1;
    	}
    	BenchMarkPartThree array = new BenchMarkPartThree(intArray);
    	List<Integer> partiallyRandom = new ArrayList<>();
    	List<Integer> random = new ArrayList<>();
    	List<Integer> ordered = new ArrayList<>();
    	List<Integer> reverseOrder = new ArrayList<>();

        for (int number : array.createPartiallyOrdered(intArray)) 
        	partiallyRandom.add(number);
        
        for (int number : array.createRandom(intArray)) 
        	random.add(number);
  
        for (int number : array.createOrder(intArray)) 
        	ordered.add(number);
        
        for (int number : array.createReverseOrder(intArray)) 
        	reverseOrder.add(number);
        

        
        
        System.out.println( getTimer(times, partiallyRandom));
        
        System.out.println( "Begin With " +  "random" + " runs " + 
				String.valueOf(times) + "times, " + "average runtimes is " +  
				String.valueOf(getTimer(times, random)) + "ms");
        
        System.out.println( "Begin With " +  "ordered" + " runs " + 
				String.valueOf(times) + "times, " + "average runtimes is " +  
				String.valueOf(getTimer(times, ordered)) + "ms");
        
        System.out.println( "Begin With " +  "reverseOrder" + " runs " + 
				String.valueOf(times) + "times, " + "average runtimes is " +  
				String.valueOf(getTimer(times, reverseOrder)) + "ms");
        
        System.out.println( "Begin With " +  "partiallyRandom" + " runs " + 
				String.valueOf(times) + "times, " + "average runtimes is " +  
				String.valueOf(getTimer(times, partiallyRandom)) + "ms");


    }
}
