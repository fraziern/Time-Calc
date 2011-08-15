package com.nickfrazier.timecalc;

import junit.framework.TestCase;

import java.text.ParseException;
import java.util.Calendar;

public class TestFive extends TestCase {

    // TODO TEST NEW FUNCTIONALITY OF PARSETIME, WHERE HOURS IN TENTHS CAN BE PARSED

    private ParseTime tp;
    private Calendar testCal;
    
    public TestFive(String name) {
        super(name);
    }

    protected void setUp() {
    
        tp = new ParseTime();
        testCal = Calendar.getInstance();
    }
    
    public void testTenths() {
        
        try {
            testCal = tp.readTime("10.6");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertTrue(tp.isTenths());
        assertEquals(testCal.get(Calendar.HOUR_OF_DAY), 10);
        assertEquals(testCal.get(Calendar.MINUTE), 36);
        // System.out.println(testCal.toString());
        
        testCal.clear();
        try {
            testCal = tp.readTime("1.0");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertEquals(testCal.get(Calendar.HOUR_OF_DAY), 1);
        assertEquals(testCal.get(Calendar.MINUTE), 0);
        // System.out.println(testCal.toString());
        
        testCal.clear();
        try {
            testCal = tp.readTime("12.5");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertEquals(testCal.get(Calendar.HOUR_OF_DAY), 12);
        assertEquals(testCal.get(Calendar.MINUTE), 30);
 
    }
    

}

