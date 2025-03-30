// Symbol Tables
// author: carson
// desc: Contains the code responsible for managing
//       the data structures for program variables

import java.util.Map;
import java.util.HashMap;

/** Symbol Table for program variables*/
public class SymbolTable {

    private static Map<String, Double> doubleVars;
    private static Map<String, String> stringVars;

    /** intializes symbol table data structures */
    public static void initialize() {
        doubleVars = new HashMap<>();
        stringVars = new HashMap<>();
    }

    /** adds a double variable to symbol table */
    public static void add(String varName, double value) {
        doubleVars.put(varName, value);

        if (IO.usingFile == false) {
            System.out.println("saved: "+varName+"="+value);
        }
    }
    /** adds a string variable to symbol table */
    public static void add(String varName, String value) {
        stringVars.put(varName, value);

        if (IO.usingFile == false) {
            System.out.println("saved: "+varName+"="+value);
        }
    }
    /** retreives value for given variable name */
    public static String getValue(String varName) {
        String value = "";

        if (hasDoubleVar(varName) == true) {
            value = String.valueOf(doubleVars.get(varName));
        } else if (hasStringVar(varName) == true) {
            value = stringVars.get(varName);
        }

        return value;
    }
    /** assigns string literal to string variable */
    public static boolean assignStringVar(String varName, String stringLiteral) {
        if (stringVars.containsKey(varName) == true) {
            stringVars.remove(varName);
            add(varName, stringLiteral);
            return true;

        } else {
            // varName is not a declared variable
            return false;
        }
    }
    /** assigns double literal to double variable */
    public static boolean assignDoubleVar(String varName, double numberLiteral) {
        if (doubleVars.containsKey(varName) == true) {
            doubleVars.remove(varName);
            add(varName, numberLiteral);
            return true;

        } else {
            // varName is not a declared variable
            return false;
        }
    }
    /** returns the list of double variables */
    public static Map<String, Double> getDoubleVars() {
        return doubleVars;
    }
    /** returns the list of string variables */
    public static Map<String, String> getStringVars() {
        return stringVars;
    }
    /** checks if string variable exists */
    public static boolean hasStringVar(String varName) {
        return stringVars.containsKey(varName);
    }
    /** checks if double variable exists */
    public static boolean hasDoubleVar(String varName) {
        return doubleVars.containsKey(varName);
    }
}
