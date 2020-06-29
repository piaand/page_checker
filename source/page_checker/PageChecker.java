package page_checker;

import java.util.*;

public class PageChecker extends TimerTask {
    static String errorClose = "Error: system will close";
    static String fileName = "config_request.txt";
    static String logName = "checker_log.txt";
    
    public void run() {
        try {
            String content;
            Log log = new Log(logName);
            log.startLog();
            FileHandler handler = new FileHandler(fileName);
            ArrayList<RequestHTTP> listRequests = handler.readConfigFile(log);
            for (RequestHTTP request : listRequests) {
                Long start = System.nanoTime();
                content = request.doRequests(log);
                if (content != null) {
                    if (!(request.getRules().isEmpty())) {
                        request.checkRules(content, log);
                    } else {
                        log.writeToLog(
                            "Url " + request.getUrl() + " has no rules to check.");
                    }
                }
                long duration = log.measureTime(start, request.getUrl());
                double seconds = (double) duration/1000000000;
                log.writeToLog(duration+" nanoseconds or "+seconds+" seconds to request for "+request.getUrl());
            }

            log.writeToLog(
                "Ended request testing");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}