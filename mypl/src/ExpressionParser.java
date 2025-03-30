// Expression Parser
// author: Carson Bragg; ai assisted
// desc: This is a recusive style expression evaluator. It iterates
//        through the expression until it finds the next available
//        symbol and checks if it is a reconized symbol. An error
//        is thrown if the parser doesn't know one of the given
//        characterss; whitespace is ignored. If it is, then the 
//        next method is called until the highest precedence is 
//        reached. Then it keeps calling itself until it works back 
//        down to the lowest precedence.

/** pareses and evaluates an expression */
public class ExpressionParser {
    private final String expr;
    private int pos = -1; 
    private int ch;

    /** Constructor */
    public ExpressionParser(String expr) {
        this.expr = expr;
    }

    /** starts parsing Expresion */
    double parse() {
        nextChar();
        double x = parseExpression();

        if (pos < expr.length()) { 
            throw new RuntimeException(Errors.unexpectedChar + (char) ch);
        }
        return x;
    }

    // -------------------------------------------------
    // ---------------- PRIVATE METHODS ---------------- 
    // -------------------------------------------------

    /** moves to next char in the expression */
    private void nextChar() {
        // ch = (++pos < expr.length()) ? expr.charAt(pos) : -1;
        pos++;
        if (pos < expr.length()) {
            ch = expr.charAt(pos);

        } else {
            // reached end of expression
            ch = -1;
        }
    }

    /** skips whitespace and finds next part of expression */
    private boolean eat(int charToEat) {
        while (ch == ' ') { 
            nextChar();
        }

        // if character matches 
        if (ch == charToEat) {
            nextChar(); // move pos to next char after ch
            return true;
        }
        return false;
    }

    /** parses an expression to a number*/
    private double parseExpression() {
        double x = parseTerm();

        for (;;) { // while true
            if (eat('+')) {
                x += parseTerm();

            } else if (eat('-')) {
                x -= parseTerm();

            } else {
                return x;
            }
        }
    }

    /** parses a term to a number */
    private double parseTerm() {
        // lowest precedence
        double x = parseFactor();

        for (;;) { // while true
            if (eat('*') == true) {
                // evaluates mult expr
                x *= parseFactor();
                
            } else if (eat('/') == true) {
                x /= parseFactor();

            } else {
                return x;
            }
        }
    }

    /** parses and evaluates expnt expr */
    private double parseFactor() {
        double x = parsePrimary();

        while (true) {
            int savedPos = pos;
            int savedCh = ch;

            if (eat('*') && eat('*')) {
                // if next two non-whitespace chars are * 
                x = Math.pow(x, parseFactor());
            } else {
                // backtrack if not actually exponentiation
                pos = savedPos;
                ch = savedCh;
                break;
            }
        }

        return x;
    }

    /** finds next number eventually */
    private double parsePrimary() {
        // highest precedence
        if (eat('+') == true) {
            return parsePrimary();
        }
        if (eat('-') == true) {
            return -parsePrimary();
        }

        double x;
        int startPos = this.pos;

        if (eat('(') == true) {
            x = parseExpression(); // parse inside paranthesises
            if (eat(')') != true) {
                // if cant find closing paranthesises throw error
                throw new RuntimeException(Errors.missingCloseParan);
            }

        } else if ((ch >= '0' && ch <= '9') || ch == '.') {
            // is start of number

            // while ch points to part of the number
            while ((ch >= '0' && ch <= '9') || ch == '.') {
                nextChar();
            } 

            String number = expr.substring(startPos, this.pos);
            x = Double.parseDouble(number);

        } else {
            throw new RuntimeException(Errors.unexpectedChar + (char) ch);
        }
        return x;
    }
}
