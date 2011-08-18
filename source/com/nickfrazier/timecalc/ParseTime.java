package com.nickfrazier.timecalc;

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
    // 
    // we need something that will take strings and convert to operators and TenthTimes
    // and I think that's it.
    // TODO: Some validation and error handling too, but that's for later.
    // 

  List<String> tokens;
  List<TenthTime> times;
  List<Int> operators;
  int numTimes;

  public static final int   // operators
  ADD = 0,
  SUB = 1;

  
  public ParseTime() {  // Constructor
  }

  // getters for operators and times
  public List<Int> getOperators { return this.operators }; // Is this OK? Does it return the right thing

  public List<TenthTime> getTimes { return this.times };

  public void readTime(List<String> in) {

    // init
    times = new ArrayList<TenthTime>;
    operators = new ArrayList<Int>;
    numTimes = in.size();
    tokens = in;

    // step through 'in'
    //  for each entry, if it's an operator, add it to operators.
    //  (if we just had an operator, ignore it.)
    //  If it's a string entry, parse it into a TenthTime.

    for(i = 0; i < numTimes; i++) {
      String s = tokens.get(i);
      if(s == "+") operators.add(ADD);
      else if(s == "-") operators.add(SUB);
      
        // We have a time. First scan to see if it's absolute or relative.
      else if(s.matches(".*\..*"))   // regex that looks for at least one period
        times.add(readTenths(s));
      else times.add(readHrsMins(s));
    }
        

  }
    private TenthTime readTenths(String time) {

        float tenths;
        int tenthsHours, tenthsMins;

        // Parse out tenths String
        tenths = Float.valueOf(time).floatValue();
        tenthsHours = (int) Math.floor(tenths);
        tenthsMins = Math.round((tenths - (float) tenthsHours) * 10 * 6);

        // Build TenthTime
        return new TenthTime(tenthsHours, tenthsMins, TenthTime.REL);
    }


    private TenthTime readHoursMins(String time) {
        
        int 
        hrs = 0,
        mins = 0,
        digitCount = 0;
        char[] digits = new char[4];
        

        // Find state of AM/PM the easy way, if possible

        boolean isPM = false;
        
        if(time.matches(".*[pP].*")) isPM = true;

        // Extract hours and mins
        // There are several ways the time
        // could be written: "1202", "12:02", "1202p M", etc.
        // or even "1" for 1 oclock. or 121 for 1:21.
        // If there's 1 or 2 digits, it's hours only.
        // If there's 3 or 4, last 2 digits are mins.

        // First extract the digits.

        // TODO throw ParseException when there are too many digits.

        for(int i=0; ( (i < time.length()) && (digitCount < 4)); i++) {
            if (Character.isDigit(time.charAt(i))) 
                digits[digitCount++] = Char.valueOf(time.charAt(i));
        }

        // Then process the hours, whether we have minutes or not.
        if(digitCount == 1 || digitCount == 3) hrs = digits[0];
        else hrs = digits[0]*10+digits[1];
        // Preserves 24-h notation, if given.

        if(isPM && hrs < 13) hrs += 12;
       
        // Then process the minutes, if we have 3 or 4 digits.
        if (digitCount > 2) 
          mins = digits[digitCount-2]*10+digits[digitCount-1];

        // Create TenthTime
        return new TenthTime(hrs, mins, TenthTime.ABS);



        // TODO: Test for 24-hour notation

    }
}
