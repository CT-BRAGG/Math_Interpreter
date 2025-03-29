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

        if (assign.getType() == Token.PossibleTokens.ASSIGN && literal.getType() == Token.PossibleTokens.STRING_LITERAL) {
            Evaluator.declareStringVariable(varToken.getValue(), literal.getValue());
        } else {
            throw new RuntimeException(Errors.badStringStatement);
        }
    }

    /** Handles lines that begin with a double variable (assignment) */
    private static void handleDoubleLine(Token first) throws Exception {
        Token second = TokenQueue.getNextToken();
        Token third = TokenQueue.getNextToken();

        if (second == null || third == null) {
            throw new RuntimeException(Errors.incompleteDoubleStatement);
        }

        if (second.getType() == Token.PossibleTokens.ASSIGN && third.getType() == Token.PossibleTokens.EXPRESSION) {
            Evaluator.assignDoubleLiteral(first.getValue(), third.getValue());
        } else {
            throw new RuntimeException(Errors.badStringStatement);
        }
    }

    /** Handles lines that begin with a string variable (assignment) */
    private static void handleStringLine(Token first) throws Exception {
        Token second = TokenQueue.getNextToken();
        Token third = TokenQueue.getNextToken();

        if (second == null || third == null) {
            throw new RuntimeException(Errors.incompleteStringStatement);
        }

        if (second.getType() == Token.PossibleTokens.ASSIGN && third.getType() == Token.PossibleTokens.STRING_LITERAL) {
            Evaluator.assignStringLiteral(first.getValue(), third.getValue());
        } else {
            throw new RuntimeException(Errors.badStringStatement);
        }
    }

    /** Handles print statements */
    private static void handlePrintLine() throws Exception {
        Token next = TokenQueue.getNextToken();

        if (next == null) {
            throw new RuntimeException(Errors.missingPrintExpression);
        }

        switch (next.getType()) {
            case EXPRESSION:
                Evaluator.printExpression(next);
                break;

            case STRING_VARIABLE:
            case DOUBLE_VARIABLE:
                String result = SymbolTable.getValue(next.getValue());
                IO.printResult(result);
                break;

            case STRING_LITERAL:
                IO.printResult(next.getValue());
                break;

            default:
                throw new RuntimeException(Errors.badPrintExpression + next.getType());
        }
    }
}
