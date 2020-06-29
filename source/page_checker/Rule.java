package page_checker;

import java.util.*;

public class Rule {
	String body;
        String category;
    
    public Rule(String category, String body) {
	this.body = body;
        this.category = category;
    }
    
    public String getBody() {
        return (this.body);
    }
    
    public String getCategory() {
        return (this.category);
    }
    
    public void mapRules() {
        //decide which rules to check according to category.
    }
    
}