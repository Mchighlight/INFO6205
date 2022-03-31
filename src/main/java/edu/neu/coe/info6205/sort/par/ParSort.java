package edu.neu.coe.info6205.sort.par;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class ParSort {

	
    public static int cutoff ;
    public static int count = 0;

    public static void setCutoff(int cutoff) {
        ParSort.cutoff = cutoff;
    }

    public static int getCutoff() {
        return cutoff;
    }

    
    public static void sort(int[] array, int from, int to,int cutoff) {
        setCutoff(cutoff);
        int size = to - from;
        int mid=from+(size / 2);
        if (size < cutoff)  Arrays.sort(array, from, to+1);
        else {           
 

            CompletableFuture<int[]> parsort1 = parsort(array, from, mid); // TODO implement me
            CompletableFuture<int[]> parsort2 = parsort(array, mid+1, to); // TODO implement me
            CompletableFuture<int[]> parsort = parsort1.thenCombine(parsort2, (xs1, xs2) -> {
                    int[] result = new int[xs1.length + xs2.length];
                        
                    int i=0;
                    int j=0;
                    for(int k=0;k<result.length;k++){
                        if(i>=xs1.length) result[k]=xs2[j++];

                        else if(j>=xs2.length) result[k]=xs1[i++];
                        
                        else if(less(xs1[i],xs2[j])) result[k]=xs1[i++];
                        
                        else result[k]=xs2[j++];
                    }
                        return result;
                    });

            parsort.whenComplete((result, throwable) -> {
                    int t=from;
                    for(int k=0;k<result.length;k++){
                        array[t]=result[k];
                        t++;
                    }


            }); // TODO implement me
            parsort.join();
        }
    }
    
    

    private static CompletableFuture<int[]> parsort(int[] array, int from, int to) {
        return CompletableFuture.supplyAsync(() -> {
                    int[] result = new int[to  - from+1];
                    // TODO implement me
                    sort(array, from, to,getCutoff());
                     int t=from;
                     
                   	 for(int i=0;i<result.length;i++)
                     {                         

                    	 result[i]=array[t];
                         t++;
                     }
                    return result;
                }
        );
    }
    
    public static boolean less(int i, int j)
    {
        if(i<=j)         
        return true;        
        else
        return false;
    }
}
