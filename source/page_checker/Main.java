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
                    String content;
                    Log log = new Log(logName);
                    log.startLog();
                    FileHandler handler = new FileHandler(fileName);
                    ArrayList<RequestHTTP> listRequests = handler.readConfigFile(log);
                    for (RequestHTTP request : listRequests) {
                        content = request.doRequests(log);
                        if (content != null) {
                            if (!(request.getRules().isEmpty())) {
                                request.checkRules(content, log);
                            } else {
                                log.writeToLog(
                                    "Url " + request.getUrl() + " has no rules to check.");
                            }
                        }
                    }
                    
                    log.writeToLog(
                        "Ended request testing");
                }
            } catch (Exception e) {
                throw e;
            }
        }
        
        public static void printInstructions() {
            System.out.println("You need more/less arguments");
        }
        
        
}