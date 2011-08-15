package com.nickfrazier.timecalc;

import java.text.ParseException;
import java.util.Calendar;

import junit.framework.TestCase;

// TESTER FOR PARSETIME CLASS

public class TestTwo extends TestCase {

    private ParseTime tp;
    private Calendar testCal;
    
    public TestTwo(String name) {
        super(name);
    }

    protected void setUp() {
    
        tp = new ParseTime();
        testCal = Calendar.getInstance();
    }
    
    public void testReader() {
        
        try {
            testCal = tp.readTime("908a");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertEquals(testCal.get(Calendar.AM_PM), Calendar.AM);
        assertEquals(testCal.get(Calendar.HOUR), 9);
        assertEquals(testCal.get(Calendar.MINUTE), 8);
        // System.out.println(testCal.toString());
        
        testCal.clear();
        try {
            testCal = tp.readTime("11:08 pm");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertEquals(testCal.get(Calendar.AM_PM), Calendar.PM);
        assertEquals(testCal.get(Calendar.HOUR_OF_DAY), 23);
        assertEquals(testCal.get(Calendar.MINUTE), 8);
        // System.out.println(testCal.toString());
        
        testCal.clear();
        try {
            testCal = tp.readTime("4:38");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertEquals(tp.isAMPMKnown(), false);
        assertEquals(testCal.get(Calendar.AM_PM), Calendar.AM);
        assertEquals(testCal.get(Calendar.HOUR_OF_DAY), 4);
        assertEquals(testCal.get(Calendar.MINUTE), 38);
 
    }
    
    public void testTokens() {
        
        String[] testTokens = tp.tokenizeTime("908a-900a");
        assertEquals(testTokens[0], "908a");
        assertEquals(testTokens[1], "900a");

    }
    

}
