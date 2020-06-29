package page_checker;

import java.util.*;
import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;

public class RequestHTTP {
	String url;
        ArrayList<Rule> rules;
    
    public RequestHTTP(String url) {
	this.url = url;
        this.rules = new ArrayList<Rule>();
    }
    
    public String getUrl() {
        return (this.url);
    }
    
    public void setConnection(HttpURLConnection con, Log log) throws IOException {
        try {
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "plain/text");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
        } catch (IOException e) {
            log.writeToLog(
                    "Error: Setting connection to " + getUrl() + " failed.");
            con.disconnect();
            throw e;
        }
    }
    
    public String readResponse(HttpURLConnection con, Log log) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = reader.readLine()) != null) {
                content.append(inputLine);
            }
            reader.close();
            String result = content.toString();
            return (result);
        } catch (IOException e) {
            log.writeToLog(
                    "Error: Reading response content from " + getUrl() + " failed.");
            con.disconnect();
            throw e;
        }
    }
    
    public void doRequests(Log log) throws Exception {
        try {
            URL url = new URL(getUrl());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            setConnection(con, log);
            int status = con.getResponseCode();
            System.out.println(status + "code from response");
            String content = readResponse(con, log);
            System.out.println(content);
        } catch (Exception e){
            String errorName = e.getClass().getSimpleName();
            log.writeToLog(
                    "Error: making request failed with url " + getUrl() + " due to " + errorName);
            e.printStackTrace();
        } 
   
    }
}