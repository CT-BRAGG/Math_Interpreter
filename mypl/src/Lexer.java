// Lexer
// author: Carson 
// desc: breaks down raw input into tokens 

import java.util.Arrays;

/** breaks down raw input into tokens */
public class Lexer {
    private static Token prevToken;
    private static boolean isExpectingVarName;
    private static Token.PossibleTokens currDeclareType;

    /** initializes lexer variables */
    public static void initialize() {
        prevToken = null;
        isExpectingVarName = false;
        currDeclareType = null;
    }
    /** starts the lexer */
    public static void run(String line) throws Exception {
        String[] lineComponents = new String[0];
        int len = 0;

        line = checkForTerminator(line);
        line = line.trim();
        lineComponents = line.split(" ");
        len = lineComponents.length;

        for (int idx = 0; idx < len; idx++) {
            String component = lineComponents[idx];
            Token token = tokenize(component);

            if (token == null) {
                throw new RuntimeException(Errors.unknownComponent+component);
            }

            //System.out.println(idx+". "+token.getValue()); // testing

            TokenQueue.addToken(token);
            prevToken = token;

             /** if just added an ASSIGN or PRINT token, rest of line is an expression */
            if (token.getType() == Token.PossibleTokens.ASSIGN || token.getType() == Token.PossibleTokens.PRINT) {
                String[] exprParts = Arrays.copyOfRange(lineComponents, idx + 1, len);
                String expr = String.join(" ", exprParts);

                //System.out.println("lexer: expr: "+expr); // testing

                Token exprToken = new Token(Token.PossibleTokens.EXPRESSION);
                exprToken.setValue(expr);

                TokenQueue.addToken(exprToken);

                break; 
            }
        }
    }
    /** starts the tokenization process */
    public static Token tokenize(String component) throws Exception {
        Token newToken = null;

        component = component.toLowerCase();
        if (isExpectingVarName == true) {
            isExpectingVarName = false;

            if (!isValidName(component)) {
                throw new RuntimeException(Errors.badVariableName + component);
            }

            return new Token(component, currDeclareType);
        }

        if (component.equals("double")) {
            // double declaration keyword
            isExpectingVarName = true;
            currDeclareType = Token.PossibleTokens.DOUBLE_VARIABLE;
            Token.PossibleTokens type = Token.PossibleTokens.DOUBLE_DECLARE;
            newToken = new Token(component, type);

        } else if (component.equals("string")) {
            // string declaration keyword
            isExpectingVarName = true;
            currDeclareType = Token.PossibleTokens.STRING_VARIABLE;
            Token.PossibleTokens type = Token.PossibleTokens.STRING_DECLARE;
            newToken = new Token(component, type);

        } else if (component.equals("print")) {
            // print keyword
            Token.PossibleTokens type = Token.PossibleTokens.PRINT;
            newToken = new Token(type);

        } /*else if (TokenQueue.isFirstTokenDoubleDeclare() == true) {
            // new double variable name
            Token.PossibleTokens type = Token.PossibleTokens.DOUBLE_VARIABLE;

            if (isValidName(component) != true) {
                throw new RuntimeException(Errors.badVariableName+component);
            }
            newToken = new Token(component, type);

        } else if (TokenQueue.isFirstTokenStringDeclare() == true) {
            // new string variable name
            Token.PossibleTokens type = Token.PossibleTokens.STRING_VARIABLE;

            if (isValidName(component) != true) {
                throw new RuntimeException(Errors.badVariableName+component);
            }
            newToken = new Token(component, type);

        }*/ 
         else if (SymbolTable.hasDoubleVar(component) == true) {
            Token.PossibleTokens type = Token.PossibleTokens.DOUBLE_VARIABLE;
            newToken = new Token(component, type);

        } else if (SymbolTable.hasStringVar(component) == true) {
            // string variable previously declared
            Token.PossibleTokens type = Token.PossibleTokens.STRING_VARIABLE;
            newToken = new Token(component, type);

        } else if (component.equals("<-")) { 
            // assignment operator
            Token.PossibleTokens type = Token.PossibleTokens.ASSIGN;
            newToken = new Token(component, type);

        } else if (prevToken != null) {
            if (prevToken.getType() == Token.PossibleTokens.ASSIGN) {
                Token.PossibleTokens type = Token.PossibleTokens.EXPRESSION;
                newToken = new Token(type);
            }
        } 

        return newToken;
    }    
    /** checks for statement terminator */
    private static String checkForTerminator(String line) throws Exception {
        if (!line.endsWith(";")) {
            throw new RuntimeException(Errors.missingSemicolon);
        }
        line = removeSemicolon(line);
        return line;
    }
    /** removes a semicolon from the end of a string */
    private static String removeSemicolon(String line) throws Exception {
        return line.substring(0, line.length() - 1);
    }
    /** checks if variable name is valid */
    private static boolean isValidName(String varName) {
        boolean isValid = true;

        for (char c: varName.toCharArray()) {
            if (Character.isLetter(c) != true) {
                isValid = false;
            }
        }

        return isValid;
    }
}
