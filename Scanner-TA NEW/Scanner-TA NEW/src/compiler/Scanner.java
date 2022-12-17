package compiler;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Scanner extends UI {

    ArrayList<ArrayList<String>> tokenTable = new ArrayList<>();
    ArrayList<String> errorList = new ArrayList<>();

    public Scanner() {
        setActionListener((ActionEvent ae) -> {
            scan(getCode());
        });
    }
    
    //this Fuction take String (Code)
    void scan(String code) {
        code = code.replaceAll("\n", " "); //change end line with one space
		code=code.replaceAll(" +", " "); //change one space or more with one space
		String words[]=code.split(" ");// split all code using space
        for (int i = 0; i < words.length; i++) // loop on all eords in array to find its token
        {
            words[i] = words[i].toLowerCase();// change any uppercase to lower case
			String token = isKeyword(words[i]);//This function to find token of keyWord
			if (token.equals("NOTDEFIND")) {
				token=isIdentifier(words[i]);
			}
			if (token.equals("NOTDEFIND")) {
				token=isOPerator(words[i]);
			}
			if (token.equals("NOTDEFIND")) {
				token=isConstant(words[i]);
			}
                        if (token.equals("NOTDEFIND")) {
				token=isComment(words[i]);
			}
            if (token.equals("NOTDEFIND")) {
                errorList.add(words[i]);
            } else {
                addToken(words[i], token);
            }
        }
        setErrorList(errorList); // if my word not found in all functions add it in ErrorList
        setLexemeTable(tokenTable); // add in LexeTable (lexeme and it token)by addToken Function
        reset();
    }
    
    // this Function take String and check if it KeyWord return "KeyWord" or not return "NOTDEFIEND"
    String isKeyword(String word) 
    {
        word = word.toUpperCase(); //change word to upperCase
        switch (word) {
            case "BEGIN":
                return "BEGIN";
            case "ENDIF":
                return "ENDIF";
            case "PARAMETERS":
                return "PARAMETERS";
            case "SET":
                return "SET";
            case "CALL":
                return "CALL";
            case "ENDUNTIL":
                return "ENDUNTIL";
            case "PROCEDURE":
                return "PROCEDURE";
            case "THEN":
                return "THEN";
            case "DECLARE":
                return "DECLARE";
            case "ENDWHILE":
                return "ENDWHILE";
            case "PROGRAM":
                return "PROGRAM";
            case "UNTIL":
                return "UNTIL";
            case "DO":
                return "DO";
            case "IF":
                return "IF";
            case "READ":
                return "READ";
            case "WHILE":
                return "WHILE";
            case "ELSE":
                return "ELSE";
            case "INTEGER":
                return "INTEGER";
            case "REAL":
                return "REAL";
            case "WRITE":
                return "WRITE";
            case "END":
                return "END";
            default:
                return "NOTDEFIND";
        }
    }
   
    // this Function take string and return string   
    /* check on String if index[0] of string is letter and the index[1] is letter or number
    if it is return "IDENTIFIER" , if not return "NOTDEFIEND"
    */ 
    String isIdentifier(String word) {
        if (Character.isAlphabetic(word.charAt(0)))//check on first character of word is Alphabet
        {
            for (int i =1 ; i<word.length() ; i++)//loop on all words
            {
                //if all characters after first character not aiphabet or digit return "NOTDEFIEND"
                if(!Character.isAlphabetic(word.charAt(i))&& !Character.isDigit(word.charAt(i)))
                {
                    return "NOTDEFIEND";
                }
            }
        
        }else 
                {
                    return "NOTDEFIEND";//if the above not done return "NOTDEFIEND"
                
                }
         return "IDENTIFIER"; //if the above done return "IDENTIFIER"

        

    }
    
    //this function check on operator if true return "OPERATOR" , if not return "NOTDEFIEND"
    String isOPerator(String word) 
    {
        /*check on my lengh word if it cosists of more then one character
        return "NOTDEFIEND" because operator cosists of one character*/
        if (word.length() > 1) 
        {
            return "NOTDEFIND";
        } else {
            switch (word.charAt(0)) {
                case '.':
                    return "Point";
                case ';':
                    return "Semicolon";
                case ',':
                    return "Comma";
                case '(':
                    return "Open Bracet";
                case ')':
                    return "Close Bracet";
                case '=':
                    return "Equal";
                case '<':
                    return "Lower Than";
                case '>':
                    return "Greater Than";
                case '!':
                    return "Exclamation mark";
                case '+':
                    return "Plus";
                case '-':
                    return "Minus";
                case '*':
                    return "Multiplication";
                case '/':
                    return "Division";
                default:
                    return "NOTDEFIND";

            }
        }
    }
    
    /* this function check if my number (one digit or more.one digit or more) if it is return
    "CONSTANT" , if not return "NOTDEFIEND"
    */
    String isConstant(String word) {
        boolean checkDot = false; //
        for (int i =0 ; i<word.length() ; i++)
        {
            if (!Character.isDigit(word.charAt(i)))//check on my character if it not digit
            {
                //check on character if it id dot and check on I don't have ant dot before
                if (word.charAt(i) == '.' && !checkDot)
                {
                    checkDot = true; // change checkDot "true"
                
                }else 
                {
                return "NOTDEFIEND";
                }
            }
        }
        
        return "Constant";
        
    }
    
    
    
    String isComment (String word) 
    {
        
            if (word.charAt(0)=='/' && word.charAt(1) == '/' )
        {
            return "Single Comment";
        } 
            else if (word.charAt(0) == '/' && word.charAt(1)=='*')
        {
            return "Multiple Comment";
        
        }
                    return "NOTDEFIEND";
        
    }

    // this function add row and add in this row the word (lexeme) and its token
    void addToken(String word, String token) //add word and its token in tokenTable 
    {
        ArrayList<String> row = new ArrayList();
        row.add(word);
        row.add(token);
        tokenTable.add(row);
    }
    // this function refresh my new update in code ( remove old code and add new line code)
    void reset() {
        tokenTable.clear();
        errorList.clear();
    }
}
