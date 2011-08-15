package com.nickfrazier.timecalc;

// See TimeCalc.java for general documentation

import java.text.ParseException;
import java.util.Calendar;

public class ParseTime {

    // This class is used to parse a Calendar object, that may 
    // or may not be fully defined.

    // Fields: ...

    // Methods:
    //      Calendar readTime(String time) - sets the fields by parsing time.
    //              Each time readTime is called, the fields are reset and set to 
    //              new values.  Returns Calendar instance.
    //      String[] tokenizeTime(String time) - tokenizes a '1200a-110a' style string

    private char[] digits = new char[4];
    private int digitCount;

    private int mins;
    private int hours;
    private int ap;
    private boolean tenths = false;  // is this a time in hours-mins, or in tenths


    public boolean isTenths() { return tenths; }

    public boolean isAMPMKnown() {
        return (ap > -1);
    }

    public Calendar readTime(String time1) throws ParseException {

        // Validation
        /*            if(time1.length() < 3) {

                // a time of '5' should mean 5 o'clock.  For now just throw an exception

                throw new ParseException("time too short!", 0);

            }
         */

        // Init

        digitCount = 0;    // counts # of digits in time.
        ap = -1; 
        hours = 0;
        mins = 0;

        time1 = time1.toUpperCase();

        Calendar cal = Calendar.getInstance();
        cal.clear();

        // First find out if we have tenths or hours:mins
        for (int i = 0; (i < time1.length()) && (tenths == false); i++) {
            if (time1.charAt(i) == '.') tenths = true;
        }

        // branch off here
        if (tenths) {
            readTenths(time1, cal);
        } else {
            readHoursMins(time1, cal);
        }

        return cal;

    }

    private void readTenths(String time2, Calendar cal2) {

        float tenths;
        int tenthsHours, tenthsMins;

        // Parse out tenths String
        tenths = Float.valueOf(time2).floatValue();
        tenthsHours = (int) Math.floor(tenths);
        tenthsMins = Math.round((tenths - (float) tenthsHours) * 10 * 6);

        // Build cal
        cal2.set(Calendar.HOUR_OF_DAY, (int) tenthsHours);
        cal2.set(Calendar.MINUTE, (int) tenthsMins);

    }

    private void readHoursMins(String time, Calendar cal) {
        // Find state of AM/PM, if possible

        for(int i=0; ((i < time.length()) && (ap == -1)); i++) {
            if (time.charAt(i) == 'A') ap = Calendar.AM;
            else if (time.charAt(i) == 'P') ap = Calendar.PM;
        }

        // Extract hours and mins
        // There are several ways the time
        // could be written: "1202", "12:02", "1202p M", etc.
        // The defining characteristic is that there are always 3 or 4 digits.
        // So we can just iterate through, extracting the digits until we end.

        // The digits go into a 4-element char array.

        // Note, this could also possibly be done by storing the offset
        //  for the minutes, and the offset and length for the hours

        // TODO throw ParseException when there are too many digits.

        for(int i=0; ( (i < time.length()) && (digitCount < 4) ); i++) {
            if (Character.isDigit(time.charAt(i))) {
                digits[digitCount++] = time.charAt(i);
            } 
        }

        if (digitCount < 3) {
            // TODO if only 1 or 2 digits, we have an hour only.
            System.out.println("Not enough digits!");

        }

        // If we have 3 total digits, then there is only 1 hour digit.
        digitCount -= 2;

        if (digitCount <= 2) {
            hours = Integer.parseInt(String.valueOf(digits, 0, digitCount));
            mins = Integer.parseInt(String.valueOf(digits, digitCount, 2));
        } else {
            System.out.println("Too many digits!");

        }

        // Create cal

        cal.set(Calendar.MINUTE, mins);

        if (ap != -1) cal.set(Calendar.AM_PM, ap); // if AM/PM undefined, leave it so.
        if ((ap == Calendar.PM) && (hours < 12)) {
            cal.set(Calendar.HOUR_OF_DAY, hours+12);
        } else {
            cal.set(Calendar.HOUR_OF_DAY, hours);
        }


        // TODO: Test for 24-hour notation

    }

    public String[] tokenizeTime(String calc) {

        return calc.split("-");

    }

}
