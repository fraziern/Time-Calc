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


import java.util.Calendar;

public class TimeCalc {
    // this class will find the difference between 2 times in a 24-hour period
    
    // 3 variables: timeout, timein, entry.
    // entry = timeout - timein
    // timeout must be later than timein
    
    // A separate class will parse strings.
    // A separate class will provide the GUI.
    
    private float diff;
    private int decPlace;
    public static final float ERROR = -1;
    private static final int NOROUND = -1;
    
    public TimeCalc() {
        decPlace = 1;        // default is 1 decimal place rounding.
    }
    
    public TimeCalc(int dec) {
        decPlace = dec;
    }
     
    public float getEntry(Calendar last, Calendar first){
        
        // Calculates diff between timein and timeout.
        // Returns ERROR if error.
        
//  These validators don't work any more.  Fix them.
/*        if(timeout.getTime() == INIT.getTime()) {
            System.out.println("Timeout is null!");
            return -1;           
        }
        
        if(timein.getTime() == INIT.getTime()) {
            System.out.println("Timein is null!");
            return -1;
        }
*/
        
        if(last.before(first)) last.add(Calendar.HOUR_OF_DAY, 12);
        diff = last.getTimeInMillis() - first.getTimeInMillis();
        diff = milsToHours(diff);
        if(decPlace != NOROUND) diff = roundUp(diff, decPlace);
           
        return diff;
       
    }
    
    private float milsToHours(float mils){
        // helper method to convert milliseconds to hours.
        
        return mils / 1000 / 60 / 60;
    }
    
    
    private float roundUp(float hours, int place) {
        
        // This is a standard rounding algorithm, using ceil to round up.
        
        float p = (float) Math.pow(10, place); 
        hours *= p;
        hours = (float) Math.ceil((double) hours);
        return (float) hours/p;
        
    }
    
    
}

