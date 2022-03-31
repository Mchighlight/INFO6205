/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import java.util.Random;

public class RandomWalk {

    private int x = 0;
    private int y = 0;

    private final Random random = new Random();

    /**
     * Private method to move the current position, that's to say the drunkard moves
     *
     * @param dx the distance he moves in the x direction
     * @param dy the distance he moves in the y direction
     */
    private void move(int dx, int dy) {
//    	System.out.println( "Before " + "( " + String.valueOf(this.x) + ", " + String.valueOf(this.y) + " )");
//    	System.out.println( "Move " + "( " + String.valueOf(dx) + ", " + String.valueOf(dy) + " )");
        this.x = this.x + dx ;
        this.y = this.y + dy ;
        //System.out.println( "Move " + "( " + String.valueOf(dx) + ", " + String.valueOf(dy) + " )" + "  After " + "( " + String.valueOf(this.x) + ", " + String.valueOf(this.y) + " )");
        // END 
    }

    /**
     * Perform a random walk of m steps
     *
     * @param m the number of steps the drunkard takes
     */
    private void randomWalk(int m) {
        // FIXME
        // END
        for (int i = 0; i < m; i++) {
        	//System.out.println("  Step: " + String.valueOf(i+1));
        	randomMove();
        }
    }

    /**
     * Private method to generate a random move according to the rules of the situation.
     * That's to say, moves can be (+-1, 0) or (0, +-1).
     */
    private void randomMove() {
        boolean ns = random.nextBoolean();
        int step = random.nextBoolean() ? 1 : -1;
        move(ns ? step : 0, ns ? 0 : step);
    }

    /**
     * Method to compute the distance from the origin (the lamp-post where the drunkard starts) to his current position.
     *
     * @return the (Euclidean) distance from the origin to the current position.
     */
    public double distance() {
        double deltaX = this.x - 0;
        double deltaY = this.y - 0;
        double result = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
        return result; 
    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     *
     * @param m the number of steps for each experiment
     * @param n the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int m, int n) {
        double totalDistance = 0;
        for (int i = 0; i < n; i++) {
        	System.out.println( "[" + String.valueOf(i+1) + " times runs]");
            RandomWalk walk = new RandomWalk();
            walk.randomWalk(m);
            totalDistance = totalDistance + walk.distance();
        }
        return totalDistance / n;
    }

    public static void main(String[] args) {
    	int m = 1 ;
        int n = 10;
        if (args.length > 1) n = Integer.parseInt(args[1]);
        double meanDistance = randomWalkMulti(m, n);
        System.out.println(m + " steps: " + meanDistance + " over " + n + " experiments");
    	  
    }

}
