package com.nickfrazier.timecalc;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class LexTime {

    // LexTime will take a string as input,
    //  and will convert it to an array of tokens.
    // e.g. " 34:23a+234.3   " is converted to
    // "34:23a","+","234.3".

    String inp;     //input
    List<String> lexed; //output

    public static final String
    PLUS = "+",
    MINUS = "-";

    public LexTime(String inp) { // Constructor
        this.inp = inp; 
        this.lexed = new ArrayList<String>();
    }

    public LexTime() {
        this.inp = new String();
        this.lexed = new ArrayList<String>();
    }

    public List<String> lexer(String input) throws ParseException {

        if(input.length() == 0) { throw new ParseException("Type something first.", 0); }

        input = input.toLowerCase().trim();

        StringBuffer w = new StringBuffer();

        int position = 0;
        char c = '0';
        while(position < input.length()) {
            c = input.charAt(position);
            switch(c) {
            case '+': 
                if(w.length() > 0) { 
                    lexed.add(w.toString());   // We're at the end of a time token
                    w.delete(0,w.length());
                }                               
                lexed.add(PLUS); 
                break;    
            case '-': 
                if(w.length() > 0) {
                    lexed.add(w.toString());
                    w.delete(0,w.length());
                }
                lexed.add(MINUS); 
                break;
            case ' ': break;
            default: w.append(c);
            }
            position++;
        }

        if(position == input.length() && w.length() > 0) lexed.add(w.toString());  // add last token.

        if(lexed.indexOf("-") == -1 && lexed.indexOf("+") == -1) {
            throw new ParseException("No operators.", 0);
        } else return lexed;
    }

    //  public static void main(String[] argv) {
    //
    //    System.out.println("--- Testing LexTime ---");
    //    String in = new String(" 12:30a+ 1:30P -2.5");
    //    List<String> out = new ArrayList<String>();
    //    LexTime lex = new LexTime();
    //    
    //    out = lex.lexer(in);
    //    
    //    for(int i=0; i < out.size(); i++) {
    //      System.out.println(out.get(i));
    //    }
    //
    //    System.out.println("--- end ---");
    //
    //  }

}
