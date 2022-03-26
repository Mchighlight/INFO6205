package edu.neu.coe.info6205.union_find;
import java.util.Random;
import java.util.Scanner;  // Import the Scanner class
/**
 * Weighted Quick Union with Path Compression
 */
public class unionFindPartTwo {
    public static int count(int n) {
        int edges = 0;
        UF uf = new UF_HWQUPC(n);
        while (uf.components() > 1) {
            int i = uniform(n);
            int j = uniform(n);
            if (uf.find(i) != uf.find(j)) uf.union(i, j);
            edges++;
        }
        return edges;
    }
    
    public static int uniform(int n) {
    	Random rand = new Random();
        if (n <= 0) throw new IllegalArgumentException("argument must be positive: " + n);
        return rand.nextInt(n);
    }


    public static void main(String[] args) {
    	
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        
        System.out.println("Enter Number of N");
        int n = myObj.nextInt();  // number of vertices
        System.out.println("Number of N is: " + String.valueOf(n));  
        
        System.out.println("Enter Number of Trial");
        int trials = myObj.nextInt();  // number of trials
        System.out.println("Number of trials is: " + String.valueOf(n));  
        int[] edges = new int[trials];              // record statistics

        // repeat the experiment trials times
        for (int t = 0; t < trials; t++) {
            edges[t] = count(n);
        }

        
        System.out.println( "Take value of n =" + String.valueOf(n) +  " number of connections after " + String.valueOf(trials) + " trial: " );
        System.out.print("[ ");
        for(int i = 0 ; i < edges.length;i++) {
        	System.out.print( edges[i] + ", " );
        }
        // report statistics
        System.out.print(" ]");
        System.out.println("");
        System.out.println("");
        System.out.println("1/2 n ln n = " + 0.5 * n * Math.log(n));
        System.out.println("mean       = " +  findAverageArray(edges));
        System.out.println("stddev     = " + calculateSD(edges));
        

    }

    
    public static int findSumArray(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }
    
    public static double findAverageArray(int[] array) {
        int sum = findSumArray(array);
        return (double) sum / array.length;
    }
    
    public static double calculateSD(int numArray[])
    {
        double sum = 0.0, standardDeviation = 0.0;
        int length = numArray.length;

        for(double num : numArray) {
            sum += num;
        }

        double mean = sum/length;

        for(double num: numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation/length);
    }
}

