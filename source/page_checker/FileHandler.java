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
    
    public void readConfigFile() throws Exception {
        try  {
            String line;
            File file = new File(getFileName()); 
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ArrayList<RequestHTTP> listRequests = new ArrayList<RequestHTTP>();
            while((line = bufferedReader.readLine()) != null) {
                try {
                    RequestHTTP request = extractRequest(line);
                    listRequests.add(request);
                } catch(Exception e) {
                    System.out.println(
                    "Error extracting exceptions");
                }                
                
            }
            System.out.println("ended reading all the lines");
            // close files.
            bufferedReader.close();

            } catch(FileNotFoundException e) {
                System.out.println("Unable to open file '" + fileName + "'");                
            } catch(IOException e) {
                System.out.println(
                    "Error reading file '" + fileName + "'");                  
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