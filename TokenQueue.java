// Token Queue Class
// author: Carson Bragg
// desc: represents the queue of tokens for a given line

import java.util.Queue;
import java.util.LinkedList;

/** Queue of Tokens */
public class TokenQueue {
    private static Queue<Token> lineOfTokens;
    
    /** intializes token queue */
    public static void initialize() {
        lineOfTokens = new LinkedList<Token>();
    }
    /** adds token to end of queue */
    public static void addToken(Token newToken) {
        lineOfTokens.add(newToken);
    }
    /** retrieves first token in queue */
    public static Token getNextToken() {
        return lineOfTokens.poll();
    }
    /** checks if first token declares double variable */
    public static boolean isFirstTokenDoubleDeclare() {
        Token token = lineOfTokens.peek();
        return token != null && token.getType() == Token.PossibleTokens.DOUBLE_DECLARE;
    }
    /** checks if first token declares string variable */
    public static boolean isFirstTokenStringDeclare() {
        Token token = lineOfTokens.peek();
        return token != null && token.getType() == Token.PossibleTokens.STRING_DECLARE;
    }
    /* 
    public static boolean isFirstTokenDoubleDeclare() {
        Token token = lineOfTokens.peek();

        if (token.getType() == Token.PossibleTokens.DOUBLE_DECLARE) {
            return true;    
        } else {
            return false;
        }
    }
    public static boolean isFirstTokenStringDeclare() {
        Token token = lineOfTokens.peek();

        if (token.getType() == Token.PossibleTokens.STRING_DECLARE) {
            return true;    
        } else {
            return false;
        }
    }
    */
    /** returns first token */
    public static Token peekFirst() {
        return lineOfTokens.peek();
    }
}
