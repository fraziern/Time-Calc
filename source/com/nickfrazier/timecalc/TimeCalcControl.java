package com.nickfrazier.timecalc;

import java.text.ParseException;

public class TimeCalcControl {
    
    TimeCalc tc = new TimeCalc();

    public String putGet(String put) throws ParseException {
      
      String output = new String();
      try {
        output = tc.process(put);
      }
      catch (ParseException e) {
        throw new ParseException(e.getMessage(), 0);
      }

      return output;

    }
    
    public String getCorrect(String bad) throws ParseException {
        
        String out = new String();
        // fix
        try {
        out = tc.getCorrect(bad);
        }
        catch (ParseException e) {
            throw new ParseException(e.getMessage(), 0); 
        }
        
        return out;
    }

}
