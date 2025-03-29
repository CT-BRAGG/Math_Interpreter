// Evaluator
// author: Carson
// desc: evaluates expressions and executes assignments

public class Evaluator {

    /** declares a double variable in the symbol table */
    public static void declareDoubleVariable(String varName, String number) throws Exception {
        double numberLiteral = Double.parseDouble(number);
        SymbolTable.add(varName, numberLiteral);
    }

    /** declares a string variable in the symbol table */
    public static void declareStringVariable(String varName, String stringLiteral) {
        SymbolTable.add(varName, stringLiteral);
    }

    /** reassigns a string literal to a variable */
    public static void assignStringLiteral(String varName, String stringLiteral) {
        boolean success = SymbolTable.assignStringVar(varName, stringLiteral);
        if (!success) {
            throw new RuntimeException(Errors.undeclaredStrinVar + varName);
        }
    }

    /** reassigns a double literal to a variable */
    public static void assignDoubleLiteral(String varName, String expr) {
        String exprValue = evaluateExpression(expr);
        double numberLiteral;
        try {
            numberLiteral = Double.parseDouble(exprValue);
        } catch (NumberFormatException e) {
            throw new RuntimeException(Errors.mustEquateToNum + expr);
        }
        boolean success = SymbolTable.assignDoubleVar(varName, numberLiteral);
        if (!success) {
            throw new RuntimeException(Errors.undeclareDoubleVar + varName);
        }
    }

    /** evaluates an expression */
    public static String evaluateExpression(String expr) {
        for (String varName : SymbolTable.getDoubleVars().keySet()) {
            double val = SymbolTable.getDoubleVars().get(varName);
            expr = expr.replaceAll("\\b" + java.util.regex.Pattern.quote(varName) + "\\b", String.valueOf(val));
        }

        try {
            return String.valueOf(evaluateMath(expr));
        } catch (Exception e) {
            throw new RuntimeException(Errors.failedToEvaluate + expr);
        }
    }

    /** evaluates a math expression with operator precedence */
    private static double evaluateMath(String expr) {
        return new ExpressionParser(expr).parse();
    }

    /** prints evaluated expression */
    public static void printExpression(Token exprToken) {
        String expr = exprToken.getValue();

        //System.out.println("eval print expr: "+expr); // testing

        if (expr.startsWith("\"") && expr.endsWith("\"")) { 
            // is string literal
            expr = expr.substring(1, expr.length() - 1); // remvoe quotes
            IO.printString(expr);

        } else if (SymbolTable.hasDoubleVar(expr)) {
            // is a double variable
            String value = SymbolTable.getValue(expr);
            IO.printResult(value);

        } else if (SymbolTable.hasStringVar(expr)) {
            // is a string variable
            String value = SymbolTable.getValue(expr);
            IO.printString(value);

        } else {
            String result = evaluateExpression(exprToken.getValue());
            IO.printResult(result);
        }
    }
}
