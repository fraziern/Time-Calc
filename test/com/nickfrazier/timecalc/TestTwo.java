package com.nickfrazier.timecalc;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

// TESTER FOR PARSETIME CLASS

public class TestTwo extends TestCase {

    private ParseTime tp;
    private List<String> testIn;
    private List<TenthTime> testOut;
    private TenthTime testTime;
    
    public TestTwo(String name) {
        super(name);
    }

    protected void setUp() {
    
        tp = new ParseTime();
        testTime = new TenthTime();
        testIn = new ArrayList<String>();
        testOut = new ArrayList<TenthTime>();
    }
    
    public void testReader() {
        
        testIn.add("908a");
        testIn.add("6:55a");
        try {
            tp.readTime(testIn);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            fail("Exception.");
        }
        testOut =    tp.getTimes();
        testTime =   testOut.get(0);
//        float f1 =   testTime.toFloat();  // Really only meant for REL values. Rounding-up errors in ABS values
                                            // Note that we could fix this by making a rounding-down method. Needed, though?
        boolean b1 = testTime.getType();
        int i1 =     testTime.getHrs();
        int i2 =     testTime.getMins();
        
//        assertEquals(f1, (float) 9.2);
        assertEquals(b1, TenthTime.ABS);
        assertEquals(i1, 9);
        assertEquals(i2, 8);
        
        testTime =   testOut.get(1);
//        f1 =    testTime.toFloat();
        b1 =    testTime.getType();
        i1 =    testTime.getHrs();
        i2 =    testTime.getMins();
        
//        assertEquals(f1, (float) 6.9);
        assertEquals(b1, TenthTime.ABS);
        assertEquals(i1, 6);
        assertEquals(i2, 55);
 
        testIn.clear();
        testIn.add("6:15p");
        testIn.add("7a");
        try {
            tp.readTime(testIn);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            fail("Exception.");
           
        }
        testOut =    tp.getTimes();
        testTime =   testOut.get(1);
//        f1 =    testTime.toFloat();
        b1 =    testTime.getType();
        i1 =    testTime.getHrs();
        i2 =    testTime.getMins();
        
//        assertEquals(f1, (float) 7.0);
        assertEquals(b1, TenthTime.ABS);
        assertEquals(i1, 7);
        assertEquals(i2, 0);
    }
    
}
