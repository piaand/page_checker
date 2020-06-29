package page_checker;
import java.text.*;
import java.util.*;

public class Main {
        static String errorClose = "Error: system will close";
        
	public static void main(String[] args) throws Exception {
            try  {
                if (args.length != 1) {
                    printInstructions();
                } else {
                    System.out.println("Success ");
                    System.out.println(args[0]);
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