// Token Class
// author: Carson Bragg
// desc: represents a token 

/** represents a token  */
public class Token {

    // variables
    private PossibleTokens tokenType;
    private String expression;
    private String number;
    private String stringLiteral;
    private String variableName;
    private boolean isDeclared;

    // all possible tokens
    enum PossibleTokens {
        DOUBLE_DECLARE,
        STRING_DECLARE,
        PRINT,
        EXPRESSION,
        ASSIGN,
        STRING_LITERAL,
        NUMBER_LITERAL,
        UNKNOWN,
        DOUBLE_VARIABLE,
        STRING_VARIABLE,
        STATEMENT_TERMINATOR
    }

    //-----------------------------------------------------
    // ------------------ CONTSTRUCTORS ------------------
    //-----------------------------------------------------

    public Token() {
        this.tokenType = PossibleTokens.UNKNOWN;
        this.expression = "";
        this.number = ""; 
        this.stringLiteral = "";
        this.variableName = "";
        this.isDeclared = false;
    }
    public Token(PossibleTokens newTokenType) {
        this.tokenType = newTokenType;
        this.expression = "";
        this.number = "";
        this.stringLiteral = "";
        this.variableName = "";
        this.isDeclared = false;
    }
    public Token(String newValue, PossibleTokens newTokenType) {
        this.tokenType = newTokenType;
        this.expression = "";
        this.number = "";
        this.stringLiteral = "";
        this.variableName = "";
        this.isDeclared = false;

        handleNewValue(newValue);
    }

    //-----------------------------------------------------
    // ------------------ PUBLIC METHODS ------------------
    //-----------------------------------------------------

    /** returns the token's value if it has one */
    public String getValue() {
        if (this.tokenType == PossibleTokens.EXPRESSION) {
            return this.expression;

        } else if (this.tokenType == PossibleTokens.STRING_LITERAL) {
            return this.stringLiteral;

        } else if (this.tokenType == PossibleTokens.NUMBER_LITERAL) { 
            return this.number;

        } else if (this.tokenType == PossibleTokens.DOUBLE_VARIABLE) {
            return this.variableName;

        } else if (this.tokenType == PossibleTokens.STRING_VARIABLE) {
            return this.variableName;

        } else {
            return "null";
        }
    }
    /** sets the value of the token */
    public void setValue(String value) {
        if (this.tokenType == PossibleTokens.EXPRESSION) {
            this.expression = value;

        } else if (this.tokenType == PossibleTokens.STRING_LITERAL) {
            this.stringLiteral = value;

        } else if (this.tokenType == PossibleTokens.NUMBER_LITERAL) { 
            this.number = value;

        } else if (this.tokenType == PossibleTokens.DOUBLE_VARIABLE) {
            this.variableName = value;

        } else if (this.tokenType == PossibleTokens.STRING_VARIABLE) {
            this.variableName = value;
        }
    }
    /** returns type of token */
    public PossibleTokens getType() {
        return this.tokenType;
    }
    /** returns true if variable has previously been declared */
    public boolean getIsDeclared() {
        return this.isDeclared;
    }
    /** mutates the isDeclared variable */
    public void setIsDeclared(boolean truthValue) {
        this.isDeclared = truthValue;
    }

    //------------------------------------------------------
    // ------------------ PRIVATE METHODS ------------------
    //------------------------------------------------------

    /** handles the assignment of the given value */
    private void handleNewValue(String newValue) {
        if (this.tokenType == PossibleTokens.EXPRESSION) {
            this.expression = newValue;

        } else if (this.tokenType == PossibleTokens.STRING_LITERAL) {
            this.stringLiteral = newValue;

        } else if (this.tokenType == PossibleTokens.NUMBER_LITERAL) { 
            this.number = newValue;

        } else if (this.tokenType == PossibleTokens.DOUBLE_VARIABLE) {
            this.variableName = newValue;

        } else if (this.tokenType == PossibleTokens.STRING_VARIABLE) {
            this.variableName = newValue;
        }
    }
}
