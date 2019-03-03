/*
 * Classname:GravityCalculator.java
 *
 * Version information:1
 *
 * Date:18/01/2019
 *@author : Anuradha Nitin Bhave and Himanshi Chetwani
 */

/**
 *Calculates the distance an object travels in the downwards direction given
 * some length of time based on gravity  and velocity of the object after that
 * length of time on earth and mars.
 */
import java.io.*;
public class GravityCalculator {
    /**
     * Function Name : Main
     * Calculates the distance an object travels in the downwards direction given
     * some length of time based on gravity  and velocity of the object after that
     * length of time on earth and mars.
     * @param args
     * @return: None
     */

    public static void argument_checker(String [] args, String planet,
                                        double time){

        /**
         * To check if the second argument is a number or not
         */
        try {
            time = Double.parseDouble(args[1]);
        }catch (NumberFormatException e){
            System.err.println("Not a number");
            System.exit(1);
        }
        /**
         * To check if the planet is earth or mars.
         */
        if(!(planet.toLowerCase().equals(("earth")) ||
                planet.toLowerCase().equals("mars"))){
            System.err.println("Wrong input. Not mars or earth");
            System.exit(1);

        }

    }
    public static void main(String[] args) {
        /**
         * Checks if there are 2 command line arguments
         */
        if(args.length != 2){
            System.err.println("Insufficient arguments ");
            System.exit(1);
        }
        String planet =  args[0];
        double time=Double.parseDouble(args[1]);
        double grav_earth = 9.807;
        double grav_mars  = 3.711;
        double dist=1;
        double velocity=1;
        argument_checker(args, planet, time);

        /**
         * Calculations
         */
        if ( planet.toLowerCase().equals("earth")){
            dist = 0.5*grav_earth*time*time;
            velocity  =  grav_earth*time;

        }
        /**
         * Calculations
         */
        else if (planet.toLowerCase().equals("mars")){
            dist=0.5*grav_mars*time*time;
            velocity=grav_mars*time;
        }
        System.out.printf("%,.2f\n", dist);
        System.out.printf("%,.2f\n", velocity);

    }
}
