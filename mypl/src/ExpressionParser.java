// Expression Parser
// author: Carson Bragg
// desc: parses a given expression

public class ExpressionParser {
    private final String expr;
    private int pos = -1, ch;

    public ExpressionParser(String expr) {
        this.expr = expr;
    }

    double parse() {
        nextChar();
        double x = parseExpression();
        if (pos < expr.length()) throw new RuntimeException(Errors.unexpectedChar + (char) ch);
        return x;
    }

    private void nextChar() {
        ch = (++pos < expr.length()) ? expr.charAt(pos) : -1;
    }

    private boolean eat(int charToEat) {
        while (ch == ' ') nextChar();
        if (ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    private double parseExpression() {
        double x = parseTerm();
        for (;;) {
            if (eat('+')) x += parseTerm();
            else if (eat('-')) x -= parseTerm();
            else return x;
        }
    }

    private double parseTerm() {
        double x = parseFactor();
        for (;;) {
            if (eat('*')) x *= parseFactor();
            else if (eat('/')) x /= parseFactor();
            else return x;
        }
    }

    private double parseFactor() {
        double x = parsePrimary();
        while (eat('*')) {
            if (eat('*')) x = Math.pow(x, parseFactor());
            else pos--;
        }
        return x;
    }

    private double parsePrimary() {
        if (eat('+')) return parsePrimary();
        if (eat('-')) return -parsePrimary();

        double x;
        int startPos = this.pos;
        if (eat('(')) {
            x = parseExpression();
            if (!eat(')')) throw new RuntimeException(Errors.missingCloseParan);
        } else if ((ch >= '0' && ch <= '9') || ch == '.') {
            while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
            x = Double.parseDouble(expr.substring(startPos, this.pos));
        } else {
            throw new RuntimeException(Errors.unexpectedChar + (char) ch);
        }
        return x;
    }
}