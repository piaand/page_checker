package page_checker;

import java.text.*;
import java.util.*;
import java.io.*;

public class Main {
        static String errorClose = "Error: system will close";
        static String fileName = "config_request.txt";
        static String logName = "checker_log.txt";
        
	public static void main(String[] args) throws Exception {
            try  {
                if (args.length != 1) {
                    printInstructions();
                } else {
                    Log log = new Log(logName);
                    log.startLog();
                    FileHandler handler = new FileHandler(fileName);
                    handler.readConfigFile();
                }
            } catch (Exception e) {
                System.out.println(errorClose);
                throw e;
            }
        }
        
        public static void printInstructions() {
            System.out.println("You need more/less arguments");
        }
        
        
}