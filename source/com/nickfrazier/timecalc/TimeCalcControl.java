package com.nickfrazier.timecalc;

import java.text.ParseException;
import java.util.Calendar;

public class TimeCalcControl {
    
    TimeCalc tc = new TimeCalc();
    ParseTime pt = new ParseTime();
    Calendar testCal1 = Calendar.getInstance();
    Calendar testCal2 = Calendar.getInstance();

    public String putGet(String put) {
        
        float returnFloat;

        // Step 1
        String[] testTokens = pt.tokenizeTime(put);

        // Step 2
        try {
            testCal1 = pt.readTime(testTokens[0]);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            testCal2 = pt.readTime(testTokens[1]);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Step 3 (getDiff)
       returnFloat = tc.getEntry(testCal1, testCal2);

       if (returnFloat == TimeCalc.ERROR) return "Error!";
       else return String.valueOf(returnFloat);
       
    }

}
