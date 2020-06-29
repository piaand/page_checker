package page_checker;

import java.io.*;
import java.util.*;

public class RequestHTTP {
	String url;
        ArrayList<Rule> rules;
    
    public RequestHTTP(String url) {
	this.url = url;
        this.rules = new ArrayList<>();
    }
    
    public String getUrl() {
        return (this.url);
    }
    
}