package com.nickfrazier.timecalc;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

// See TimeCalc.java for general documentation


public class ParseTime {

    // This class is used to parse tokens from LexTime, that may 
    // or may not be fully defined.

    // Fields:
    //  a public list of n TenthTimes
    //  a public list of n-1 operators 
    //  a private list of parsed strings

    // Methods:
    //      
    // void readTime(List<String> in)   : the primary input method
    // List<TenthTime> getTimes()        : the primary output method

    // TODO: Some validation and error handling too, but that's for later.
    // 

  List<String> tokens;
  List<TenthTime> times;
  List<Integer> operators;
  int numTimes;
  TimeTrainer spellcheck = new TimeTrainer();

  public static final int   // operators
  ADD = 0,
  SUB = 1;

  
  public ParseTime() {  // Constructor
  }

  // getters for operators and times
  public List<Integer> getOperators() { return this.operators; } // Is this OK? Does it return the right thing

  public List<TenthTime> getTimes() { return this.times; }

  public void readTime(List<String> in) throws ParseException {

    // init
    times = new ArrayList<TenthTime>();
    operators = new ArrayList<Integer>();
    numTimes = in.size();
    tokens = in;
  
    // step through 'in'
    //  for each entry, if it's an operator, add it to operators.
    //  (if we just had an operator, ignore it.)
    //  If it's a string entry, parse it into a TenthTime.

    for(int i = 0; i < numTimes; i++) {
      String s = tokens.get(i);
      if(s == "+") operators.add(ADD);
      else if(s == "-") operators.add(SUB);
            
        // We have a time. First scan to see if it's absolute or relative.
      else if(s.matches(".*[.].*"))   // regex that looks for at least one period
        times.add(readTenths(s));
      else times.add(readHoursMins(s));
    }
        

  }
    private TenthTime readTenths(String time) throws ParseException {

        float tenths;
        int tenthsHours, tenthsMins;

        // Parse out tenths String
        tenths = Float.valueOf(time).floatValue();
        tenthsHours = (int) Math.floor(tenths);
        tenthsMins = Math.round((tenths - (float) tenthsHours) * 10 * 6);
        
        // Build TenthTime
        validate(tenthsHours, tenthsMins);
        return new TenthTime(tenthsHours, tenthsMins, TenthTime.REL);
    }


    private TenthTime readHoursMins(String time) throws ParseException {
        
        int 
        hrs = 0,
        mins = 0,
        digitCount = 0;
        int[] digits = new int[4];

        // Find state of AM/PM the easy way, if possible

        boolean isPM = false;
        if(time.matches(".*[pP].*")) isPM = true;

        // Extract hours and mins
        // There are several ways the time
        // could be written: "1202", "12:02", "1202p M", etc.
        // or even "1" for 1 oclock. or 121 for 1:21.
        //  2 digits could mean minutes only eg :30. 3 digits could be 0:30.
        // So if we have a colon, last 2 digits are always minutes, even if only 2 digits

        // First extract the digits.
        // TODO throw ParseException when there are too many digits.

        for(int i=0; ( (i < time.length()) && (digitCount < 4)); i++) {
            if (Character.isDigit(time.charAt(i))) 
                digits[digitCount++] = Character.getNumericValue(time.charAt(i));
        }

        // Check for a colon. If colon, process accordingly.
        if(time.matches(".*[:].*")) {
          mins = digits[digitCount-2]*10+digits[digitCount-1];
          if (digitCount == 3) hrs = digits[0];
          else if (digitCount == 4) hrs=digits[0]*10+digits[1];
        } else {

          if(digitCount == 1 || digitCount == 3) hrs = digits[0];
          else hrs = (int) (digits[0]*10+digits[1]);
          // Preserves 24-h notation, if given.

        // Then process the minutes, if we have 3 or 4 digits and no colon.
          if (digitCount > 2) 
            mins = (int) (digits[digitCount-2]*10+digits[digitCount-1]);
        } 
        if(isPM && hrs < 12) hrs += 12;   // 1-11 pm represented as 24 h time.
        if(!isPM && hrs == 12) hrs = 0;   // 12am represented as 0.
        
        // Create TenthTime
        validate(hrs, mins);
        return new TenthTime(hrs, mins, TenthTime.ABS);

    }
    
    private void validate (int hours, int mins) throws ParseException {
        if (hours < 0 || hours > 23 || mins < 0 || mins > 59) {
            throw new ParseException("Time out of bounds.", 0);
        }
    }
}
