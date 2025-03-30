# My Programming Language

version: v1.7       
numOfLines: ~ 1140

A lightweight, interpreted programming language created for educational purposes. It supports variable declarations, basic arithmetic expressions with operator precedence and parentheses, and string variables using a custom-built lexer, parser, and evaluator system written in Java. It can either be run as a like terminal like python or with a .mpl file.  

---

## How It Works

1. **Input Handling**
   - The `Main` class initializes components and reads from a file or stdin.

2. **Lexical Analysis**
   - The `Lexer` breaks input lines into tokens, using rules to identify keywords, variable names, literals, and operators.
   - Entire expressions are captured as a single token if they follow an assignment or `print`.

3. **Parsing**
   - The `Parser` reads the token queue and interprets the statement based on its structure (e.g., declaration, assignment, print).

4. **Evaluation**
   - Expressions are evaluated using a recursive-descent parser (`ExpressionParser`) that handles arithmetic and parentheses.
   - Values are stored in symbol tables (for `double` and `string` types).

---

## Installing & Running

###  Prerequisites
- Java JDK 11 or higher
- A terminal or shell (Linux/macOS/Windows)

###  Run
```bash
java -jar mypl.jar # for cli interface
java -jar mypl.jar myplSourceCodeFile.mpl # alternate method for file input
```

---

##  Syntax and Rules

###  Variable Declarations
```mpl
double x <- 3;
string msg <- "hello world";

Only a number can be assigned when declaring a double variable.
The statement "double x <- 3 + 4;" currently throws an error.

```

###  Assignments
```mpl
x <- x + 2;
msg <- "goodbye";
```

###  Print Statements
```mpl
print x;
print msg;
print (x + 3) * 2;
```

###  Expressions
- Fully parenthesized and operator-precedence aware.
- Supported: `+`, `-`, `*`, `/`, `**` (exponentiation)

###  Naming Rules
- Variable names must contain only letters (no digits or symbols).
- Statements must end in `;`.

###  Error Handling
- Missing semicolon, unknown components, invalid variable names, undeclared variables, and invalid expressions are reported with custom error messages.
- The evaluator throws an error if more than one command is on a line.

---

*readme mostly generated by chatgpt
