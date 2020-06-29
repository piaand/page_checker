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
                    Timer timer = new Timer();
                    timer.schedule(new PageChecker(), 0, 5000);
                }
            } catch (Exception e) {
                throw e;
            }
        }
        
        public static void printInstructions() {
            System.out.println("You need more/less arguments");
        }
        
        
}