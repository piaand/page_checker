package page_checker;

import java.io.*;
import java.util.*;

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
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);  
            }   
            // close files.
            bufferedReader.close();

            } catch(FileNotFoundException e) {
                System.out.println("Unable to open file '" + fileName + "'");                
            } catch(IOException e) {
                System.out.println(
                    "Error reading file '" + fileName + "'");                  
            }
    } 
}