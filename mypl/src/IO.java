// Input and Output Class
// desc: Encapsulates the input and output operations
// author: Carson Bragg

import java.util.Scanner; 
import java.io.File;
import java.io.FileNotFoundException;

/** Methods for Program Input and Output */
public class IO {
	
    // variables
    public static Scanner input;
    private static File sourceCodeFile; 
    private static boolean hasValidFile;
    public static boolean usingFile;

    // -------------------------------------------------
    // ---------------- PUBLIC METHODS ----------------- 
    // -------------------------------------------------

    public static void printError(String error) {
        System.out.println("ERROR: "+error);
    }
    /** performs intializing operations */
    public static void initialize(String[] args) {
        checkForFile(args);

        try {
            if (hasValidFile == false) {
                System.out.println("using stdin.\n");
                input = new Scanner(System.in);
                usingFile = false;
            } else {
                System.out.println("using file.\n");
                input = new Scanner(sourceCodeFile);
                usingFile = true;
            }
        } catch (FileNotFoundException ex) {
            IO.printError(Errors.badFileName+sourceCodeFile);
            input = new Scanner(System.in);
            usingFile = false;
        }
    }
    /** prints string literal */
    public static void printString(String stringLiteral) {
        System.out.println(stringLiteral);
    }
    /** prints result to console */
    public static void printResult(double result) {
        print(String.valueOf(result));
    }
    /** prints result to console */
    public static void printResult(String result) {
        print(result);
    }
    
    // -------------------------------------------------
    // ---------------- PRIVATE METHODS ---------------- 
    // -------------------------------------------------

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
        String name = "#"; // null

        // has too end with '.mpl'
        if (!fileName.endsWith(".mpl")) {
            System.out.println("file name needs to end with '.mpl'");
            validity = false;
        }

        // needs one period
        numOfPeriods = countChar(fileName, period);
        if (numOfPeriods != 1) {
            System.out.println("file name has too many periods");
            validity = false;
        }

        name = fileName.split("\\.")[0];
        System.out.println("name: "+name);

        // needs to be numbers and letters only
        if (isAlphaNumeric(name) == false) {
            System.out.println("file name not alphanumeric.");
            validity = false;
        }
        System.out.println("validFileName: "+validity);

        return validity;
    }
}
