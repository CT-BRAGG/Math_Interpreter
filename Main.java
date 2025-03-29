// Interpreter Main Entry Point 
// desc: calls the Interpreter
// author: Carson Bragg

import java.io.FileNotFoundException;
import java.util.Scanner;

/** Entry Point for Interpreter */
public class Main {
    private static Scanner scanner;

    /** handles all program errors */
    public static void main(String[] args) {

        System.out.print("Welcome to My Programming Language");
        while(true) {
            try {

                intializeObjects(args);
                runLanguageInterpreter();


            } catch (FileNotFoundException ex) { 
                IO.printError(Errors.badFileName);

            } catch (NumberFormatException ex) {
                IO.printError(Errors.notANumber);

            } catch (RuntimeException ex) { 
                IO.printError(ex.getMessage());

            } catch (Exception ex) {
                IO.printError(Errors.bigOopies);
            }
        }
    }
    /** calls all program initializers */
    public static void intializeObjects(String[] args) throws Exception{
        SymbolTable.initialize();
        IO.initializeIO(args);
        Lexer.initialize();
        TokenQueue.initialize();
        scanner = new Scanner(System.in);
    }
    /** main program loop for interpreter */
    public static void runLanguageInterpreter() throws Exception{
        System.out.print("\n>> ");

        // main program loop
        while (IO.input.hasNextLine() == true) {
            String line = IO.input.nextLine().trim();

            if (line.isEmpty()) continue;

            Lexer.run(line);
            Parser.run();
            System.out.print("\n>> ");
        }
    }
}

