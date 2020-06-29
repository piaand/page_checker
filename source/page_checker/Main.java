package page_checker;

import java.text.*;
import java.util.*;
import java.io.*;

public class Main {
        
	public static void main(String[] args) throws Exception {
            try  {
                if (args.length != 1) {
                    printInstructions();
                } else {
                    long cycle = parseCycle(args[0]);
                    System.out.println("Started the program repeting check every "+ args[0] + " hours.");
                    Timer timer = new Timer();
                    timer.schedule(new PageChecker(), 0, cycle);
                }
            } catch (Exception e) {
                throw e;
            }
        }
        
        public static long parseCycle(String hours) throws Exception {
            try {
                long hours_long = Long.parseLong(hours);
                long nanos = hours_long * 60 * 60 * 1000;
                if (nanos < 1) {
                    throw new java.lang.RuntimeException("Error: 0 or smaller cannot be repeated. Give argument 1 or higher");
                } else {
                    return (nanos);
                }
            } catch (Exception e){
                System.out.println("Please give the argument in hours the program should be repeated.");
                throw e;
            }
        }
        
        public static void printInstructions() {
            System.out.println("Start program by giving it only one argument. That argument represents the amount of hours the program should be repeated. Amount can be minimun of 1.");
        }
        
        
}