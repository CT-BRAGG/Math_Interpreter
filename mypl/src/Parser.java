// Parser Class
// author: Carson Bragg
// desc: Executes tokenized instructions

/** Parses Language into executable instructions */
public class Parser {

    /** starts the parser */
    public static void run() throws Exception{
        Token first = TokenQueue.getNextToken();
        if (first == null) return;

        switch (first.getType()) {
            case DOUBLE_DECLARE:
                handleDoubleDeclaration();
                break;

            case STRING_DECLARE:
                handleStringDeclaration();
                break;

            case DOUBLE_VARIABLE:
                handleDoubleLine(first);
                break;

            case STRING_VARIABLE:
                handleStringLine(first);
                break;

            case PRINT:
                handlePrintLine();
                break;

            default:
                throw new RuntimeException(Errors.badStartOfLine + first.getType());
        }
    }

    // -----------------------------------------------------
    // ------------------ PRIVATE MEHTODS ------------------
    // -----------------------------------------------------

    /** Handles declaration of a new double variable */
    private static void handleDoubleDeclaration() throws Exception{
        Token varToken = TokenQueue.getNextToken();
        Token assign = TokenQueue.getNextToken();
        Token expr = TokenQueue.getNextToken();

        if (varToken == null || assign == null || expr == null) {
            throw new RuntimeException(Errors.incompleteDoubleStatement);
        }


        if (assign.getType() == Token.PossibleTokens.ASSIGN && expr.getType() == Token.PossibleTokens.EXPRESSION) {
            Evaluator.declareDoubleVariable(varToken.getValue(), expr.getValue());
        } else {
            throw new RuntimeException(Errors.badStringStatement);
        }
    }

    /** Handles declaration of a new string variable */
    private static void handleStringDeclaration() throws Exception{
        Token varToken = TokenQueue.getNextToken();
        Token assign = TokenQueue.getNextToken();
        Token literal = TokenQueue.getNextToken();

        if (varToken == null || assign == null || literal == null) {
            throw new RuntimeException(Errors.incompleteStringStatement);
        }
        String expr = literal.getValue();

        /* //testing 
        System.out.println("var: "+varToken.getValue());
        System.out.println("assign: "+assign.getValue());
        System.out.println("literal: "+literal.getValue());
        System.out.println("assigntype: "+assign.getType());
        System.out.println("literalType: "+literal.getType());
        */

        if (assign.getType() == Token.PossibleTokens.ASSIGN && 
                    literal.getType() == Token.PossibleTokens.EXPRESSION) {
            //System.out.println("inside if statement"); // testing

            if (expr.startsWith("\"") && expr.endsWith("\"")) {
                // expression is a string literal
                Evaluator.declareStringVariable(varToken.getValue(), literal.getValue());

            } else {
                throw new RuntimeException(Errors.badStringStatement);
            }
        } else {
            throw new RuntimeException(Errors.badStringStatement);
        }
    }

    /** Handles lines that begin with a double variable */
    private static void handleDoubleLine(Token first) throws Exception {
        Token second = TokenQueue.getNextToken();
        Token third = TokenQueue.getNextToken();

        if (second == null || third == null) {
            throw new RuntimeException(Errors.incompleteDoubleStatement);
        }

        if (second.getType() == Token.PossibleTokens.ASSIGN && 
                    third.getType() == Token.PossibleTokens.EXPRESSION) {
            // valid double assigment grammar
            Evaluator.assignDoubleLiteral(first.getValue(), third.getValue());

        } else {
            // bad double line grammar
            throw new RuntimeException(Errors.badStringStatement);
        }
    }

    /** Handles lines that begin with a string variable */
    private static void handleStringLine(Token firstToken) throws Exception {
        Token secondToken = TokenQueue.getNextToken();
        Token thirdToken = TokenQueue.getNextToken();

        if (secondToken == null || thirdToken == null) {
            throw new RuntimeException(Errors.incompleteStringStatement);
        }

        if (secondToken.getType() == Token.PossibleTokens.ASSIGN && 
                    thirdToken.getType() == Token.PossibleTokens.STRING_LITERAL) {
            // valid string assignment grammar
            Evaluator.assignStringLiteral(firstToken.getValue(), thirdToken.getValue());

        } else {
            // bad string line grammar
            throw new RuntimeException(Errors.badStringStatement);
        }
    }

    /** Handles print statements */
    private static void handlePrintLine() throws Exception {
        Token nextToken = TokenQueue.getNextToken();

        if (nextToken == null) {
            throw new RuntimeException(Errors.missingPrintExpression);
        }

        switch (nextToken.getType()) {
            case EXPRESSION:
                Evaluator.printExpression(nextToken);
                break;

            case STRING_VARIABLE:
            case DOUBLE_VARIABLE:
                String result = SymbolTable.getValue(nextToken.getValue());
                IO.printResult(result);
                break;

            case STRING_LITERAL:
                IO.printResult(nextToken.getValue());
                break;

            default:
                throw new RuntimeException(Errors.badPrintExpression+ 
                        nextToken.getType());
        }
    }
}
