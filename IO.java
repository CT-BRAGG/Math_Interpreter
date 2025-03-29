// Input and Output Class
// desc: Encapsulates the input and output operations
// author: Carson Bragg

import java.util.Scanner; 
import java.io.File;

/** Methods for Program Input and Output */
public class IO {

    public static Scanner input;
    private static File sourceCodeFile; 
    private static boolean hasValidFile;

    // ---------------- PUBLIC METHODS  ---------------- 

    public static void printError(String error) {
        System.out.println("ERROR: "+error);
    }
    public static void initializeIO(String[] args) throws Exception {
        checkForFile(args);

        if (hasValidFile == false) {
            input = new Scanner(System.in);
        } else {
            input = new Scanner(sourceCodeFile);
        }
    }
    /** prints result to console */
    public static void printResult(double result) {
        print(String.valueOf(result));
    }
    /** prints result to console */
    public static void printResult(String result) {
        print(result);
    }
    
    //  ---------------- PRIVATE METHODS ---------------- 

    /** hidden print method */
    private static void print(String result) {
        System.out.println("result: "+result);
    }
    /** retrieves file data if valid file Address is provided */
    private static void checkForFile(String[] args) {
        String fileName = "";
        hasValidFile = false;

        if (args.length > 0) {
            fileName = args[0];

            if (validFileName(fileName) == true) {
                sourceCodeFile = new File(fileName);
                hasValidFile = true;
            }
        }
    }
    /** counts the occurence of a char */
    private static int countChar(String str, char target) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == target) {
                count++;
            }
        }
        return count;
    }
    /** checks if string is all nums and letters only */
    private static boolean isAlphaNumeric(String str) {
        char[] strChars = str.toCharArray();
        boolean result = true;

        for (char c: strChars) {
            if (!Character.isLetterOrDigit(c)) {
                result = false;
            }
        }

        return result;
    }
    /** checks if a give string is a valid program file name */
    private static boolean validFileName(String fileName) {
        boolean validity = true;
        char period = 46; 
        int numOfPeriods;

        // has too end with .mpl
        if (!fileName.endsWith(".mpl")) {
            validity = false;
        }

        // needs one period
        numOfPeriods = countChar(fileName, period);
        if (numOfPeriods != 1) {
            validity = false;
        }


        // needs to be numbers and letters only
        if (isAlphaNumeric(fileName) == false) {
            validity = false;
        }

        return validity;
    }
}
