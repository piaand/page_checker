package page_checker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

public class Log {
    String logName;
    
    public Log(String logName) {
        this.logName = logName;
    }
    
    public String getLogName() {
        return (this.logName);
    }
    
    public void startLog() {
            try {
                File logFile = new File(getLogName());
                if (logFile.createNewFile()) {
                  System.out.println("File created: " + logFile.getName());
                } else {
                  System.out.println(logFile.getName() +" file already exists.");
                }
                writeToLog("Started logging requests");
              } catch (IOException e) {
                System.out.println("Error: error creating logfile.");
                e.printStackTrace();
              }
        }
        
        public void writeToLog(String content) throws IOException {
        
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(getLogName(), true));
                writer.write(getTimeString() + "    ");
                writer.write(content);
                writer.newLine();
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("Error: error writing to logfile.");
                e.printStackTrace();
            }
        }
        
        public long measureTime(Long start, String url) throws Exception {
            try {
                long stop = System.nanoTime();
                long nanos = stop - start;
                return nanos;
            } catch (Exception e) {
                System.out.println("Error: error with measuring time");
                throw e;
            }
        }
        
        public String getTimeString() {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();
            String timestamp = dtf.format(now).toString();
            return (timestamp);
        }
    
}