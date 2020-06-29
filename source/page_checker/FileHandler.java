package page_checker;

import java.io.*;
import java.util.*;
import java.net.URL; 

public class FileHandler {
    String fileName;
    
    public FileHandler(String fileName) {
		this.fileName = fileName;
	}
    
    public String getFileName() {
        return (this.fileName);
    }
    
    public ArrayList<RequestHTTP> readConfigFile(Log log) throws Exception {
        try  {
            String line;
            int i;
            
            File file = new File(getFileName()); 
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ArrayList<RequestHTTP> listRequests = new ArrayList<RequestHTTP>();

            i = 0;
            while((line = bufferedReader.readLine()) != null) {
                try {
                    RequestHTTP request = extractRequest(line);
                    listRequests.add(request);
                } catch(Exception e) {
                    log.writeToLog(
                        "Error extracting requests at line " + i);
                }                
                i++;
            }
            System.out.println(listRequests.isEmpty());
            // close files
            bufferedReader.close();
            log.writeToLog(
                        "Read all the pages and rules");
            return (listRequests);
        } catch(FileNotFoundException e) {
            System.out.println("Unable to open file '" + fileName + "'");
            throw e;
        } catch(IOException e) {
            System.out.println(
                "Error reading file '" + fileName + "'");
            throw e;
        }
    }
    
    public RequestHTTP extractRequest(String line) {
        String url = extractURL(line);
        if (isValidURL(url)) {
            RequestHTTP request = new RequestHTTP(url);
            return (request);
        } else {
            throw new java.lang.RuntimeException("Error: line has no valid URL address.");
        }
    }
    
    public String extractURL(String line) {
        char delimiter = ';';
        
        int index = line.indexOf(delimiter);
        if (index == -1) {
            return (line);
        }
        String url = line.substring(0, index);
        return (url);
    }
    
    public boolean isValidURL(String url) 
    { 
        try { 
            new URL(url).toURI(); 
            return true; 
        } 
        catch (Exception e) { 
            return false; 
        } 
    } 
}