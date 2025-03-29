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
        String welcomeMessage = "Welcome to My Programming Language.\nLost? Enter 'help;'";
        IO.initializeIO(args);

        System.out.print(IO.usingFile?"":welcomeMessage);


        while(true) {
            try {
                intializeObjects(args);
                runLanguageInterpreter();

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
        Lexer.initialize();
        TokenQueue.initialize();
        scanner = new Scanner(System.in);
    }
    /** main program loop for interpreter */
    public static void runLanguageInterpreter() throws Exception{
        System.out.print(IO.usingFile?"":"\n>> ");

        // main program loop
        while (IO.input.hasNextLine() == true) {
            String line = IO.input.nextLine().trim();

            if (line.isEmpty()) continue;
            if (line.equals("exit;")) {
                System.out.println("\nadios.");
                System.exit(0);
            }
            if (line.equals("help;")) {
                HelpMenu.print();
                System.out.print(IO.usingFile?"":">> ");
                continue;
            }

            Lexer.run(line);
            Parser.run();

            System.out.print(IO.usingFile?"":"\n>> ");
        }
    }
}

