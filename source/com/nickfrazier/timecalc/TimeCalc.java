package com.nickfrazier.timecalc;

// TimeCalc works as follows:
//      CalcGui is the Swing GUI component, and contains the main() function for now.  When run, 
//      it presents inputs and outputs
//      for calculating time differences.  A regular string is entered, e.g.
//      '1208p-1100a'.  When enter is pressed, the difference is shown.
//
//      This follows the MVC scheme.
//              M(odel): TimeCalc, ParseTime classes
//              V(iew): CalcGui class
//              C(controller): TimeCalcController class


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TimeCalc {
    // this class will take an expression in string.
    // and output a result out string.
    
    // A separate class will parse strings. (2 classes: LexTime and ParseTime)
    // Together, TimeCalc, ParseTime, and LexTime provide the Model code.
    // A separate class will provide the View. (CalcGui)
    // A separate class will be the Controller. (TimeCalcControl)
    
    // The time object model is contained in TenthTime class. All Model code uses this class.
    
    public static final int UNREADABLE_ERROR = 1;
    
    private String errorMsg = "";
    
    public TimeCalc() {
    }
    
    public String process(String input) throws ParseException {
      
      // Reset error code
      errorMsg = "";
        
      // Step 1 Lex and Parse

      LexTime lt = new LexTime();
      ParseTime pt = new ParseTime();
      List<TenthTime> times = new ArrayList<TenthTime>();
      List<String> lexedString = new ArrayList<String>();
      
      try {
        lexedString = lt.lexer(input);
      } catch (ParseException e) {
          errorMsg = e.getMessage();
          throw new ParseException(errorMsg, 0);
      }
    
      pt.readTime(lexedString);
      times = pt.getTimes();
      
      // Step 2 Crunch

      TenthTime A = new TenthTime();
      int num = times.size();

      A = times.get(0);
      for(int i=1; i < num; i++) {
        // if minus
        // Note that minus() can handle the common '5-9' notation
        A = A.minus(times.get(i));
      }
      
      // Step 3 Format and Output

      return A.toFloatString();


       
    }
    
    public String lastError() { 
        return errorMsg; 
        }
    
    public String getCorrect(String s) throws ParseException {
        
        LexTime lt = new LexTime();
        List<String> lexedString = new ArrayList<String>();
        List<String> fixedString = new ArrayList<String>();
        TimeTrainer spellcheck = new TimeTrainer();
        
        try {
          lexedString = lt.lexer(s);
        } catch (ParseException e) {
            errorMsg = e.getMessage();
            throw new ParseException(errorMsg, 0);
        }
        
        fixedString = spellcheck.correktor(lexedString);
        String out = "";
        for(String t : fixedString) {
            out += t;
        }
        return out;
    }
    
}

