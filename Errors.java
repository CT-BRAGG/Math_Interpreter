// Error Class
// author: Carson Bragg
// desc: Contains all possible the Possible Errors

/** represents all possible errors */
public class Errors {
    // main errors
    public static final String bigOopies = "something went terribly wrong.";
    public static final String notANumber = "expression needs to be equal to a number.";

    // IO Errors
    public static final String badFileName = "Program File name is invalid.";

    // Lexer Errors
    public static final String missingSemicolon = "Syntax error: missing semicolon.";
    public static final String missingQuoteMarks = "String value must be in double quotes.";
    public static final String unknownComponent = "unknown component: ";
    public static final String badVariableName = "invalid variable name: ";

    // Parser Errors
    public static final String badStringStatement = "Malformed string statement";
    public static final String badPrintExpression = "Invalid print target: "; 
    public static final String incompleteStringStatement = "Incomplete string statement";
    public static final String incompleteDoubleStatement = "Incomplete double statement";
    public static final String badStartOfLine = "Unexpected start of line: ";

    //Expression Parers Errors
    public static final String unexpectedChar = "Unexpected: ";
    public static final String missingCloseParan = "Missing closing parenthesis";

    // Evaluator Errors
    public static final String missingPrintExpression = "Missing expression after print";
    public static final String undeclaredStrinVar = "Assignment failed: undeclared string variable ";
    public static final String mustEquateToNum = "Expression must evaluate to a number: ";
    public static final String undeclareDoubleVar = "Assignment failed: undeclared double variable ";
    public static final String failedToEvaluate = "Failed to evaluate expression: ";

}
