// Error Class
// author: Carson Bragg
// desc: Contains Program Errors

/** contains program errors */
public class Errors {
    // Main Errors
    public static final String bigOopies = "something went terribly wrong.";

    // IO Errors
    public static final String badFileName = "IO: Program File name is invalid.";

    // Lexer Errors
    public static final String missingSemicolon = "Lexer: Syntax error: missing semicolon.";
    public static final String missingQuoteMarks = "Lexer: String value must be in double quotes.";
    public static final String unknownComponent = "Lexer: unknown component: ";
    public static final String badVariableName = "Lexer: invalid variable name: ";

    // Parser Errors
    public static final String badStringStatement = "Parser: Malformed string statement";
    public static final String badPrintExpression = "Parser: Invalid print target: "; 
    public static final String incompleteStringStatement = "Parser: Incomplete string statement";
    public static final String incompleteDoubleStatement = "Parser: Incomplete double statement";
    public static final String badDoubleStatement = "Parser: malformed double statement";
    public static final String badStartOfLine = "Parser: Unexpected start of line: ";

    //Expression Parers Errors
    public static final String unexpectedChar = "ExprParser: Unexpected: ";
    public static final String missingCloseParan = "ExprParser: Missing closing parenthesis";

    // Evaluator Errors
    public static final String missingPrintExpression = "Eval: Missing expression after print";
    public static final String undeclaredStrinVar = "Eval: Assignment failed: undeclared string variable ";
    public static final String mustEquateToNum = "Eval: Expression must evaluate to a number: ";
    public static final String undeclareDoubleVar = "Eval: Assignment failed: undeclared double variable ";
    public static final String failedToEvaluate = "Eval: Failed to evaluate expression: ";
    public static final String notANumber = "Eval: expression needs to be equal to a number.";
}
