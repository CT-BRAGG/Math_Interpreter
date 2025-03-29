// Help Menu Class
// author: Carson Bragg
// desc: Contains the help menu

/** Help Menu */
public class HelpMenu {
    /** prints the help menu */
    public static void print() {
        String helpMenu = "\nHELP MENU\n"+
                "--------------------------------------------------------------\n"+
                "\n"+
                "Variables:\n"+
                "\t- declare double: 'double varName <- number\n"+
                "\t- declare string: 'string varName <- \"letters\"\n"+
                //"\n\n"+
                "Assigment:\n"+
                "\t- with math expression: 'varName <- (expr) + expr;'\n"+
                "\t- with literal: \n"+
                "\t\t- string: 'stringVar <- \"literal\";'\n"+
                "\t\t- double: 'doubleVar <- 123;'\n"+
                "\t- with other variable:\n"+
                "\t\t- string: 'var2 <- stringVar;'\n"+
                "\t\t- double: 'var3 <- doubleVar;'\n"+
                //"\n\n"+
                "Operators:\n"+
                "\t- ';'  = statement terminator\n"+
                "\t- '<-' = assignment\n"+
                "\t- '+'  = addition\n"+
                "\t- '-'  = subtraction\n"+
                "\t- '*'  = multiplication\n"+
                "\t- '/'  = division\n"+
                "\t- '**' = exponent\n"+
                "\t- '()' = paranthesis\n"+
                "Keywords:\n"+
                "\t- 'help'    = displays this menu\n"+
                "\t- 'exit'    = exits the interpreter\n"+
                "\t- 'string'  = declares a string variable\n"+
                "\t- 'double'  = declares a double variable\n"+
                "\t- 'print'   = prints an expression\n"+
                "Rules and Whatnot:\n"+
                "\t- each token needs exactly one space inbetween\n"+
                "\t- follows java operator precendence\n"+
                "\t- every statmenet must end with the statement terminator\n"+
                "\t- must use a string/number literal to declare a variable\n"+
                "\t- decimals are optional for number literals\n"+
                "\t- string literals must be enclosed in double quotes\n";

        System.out.println(helpMenu);
    }
}
