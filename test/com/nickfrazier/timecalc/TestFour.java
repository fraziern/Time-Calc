package com.nickfrazier.timecalc;

import java.text.ParseException;

import junit.framework.TestCase;

// UNIT TEST FOR TIMECALCCONTROL CLASS

public class TestFour extends TestCase {
    
    TimeCalcControl controller = new TimeCalcControl();
    
    public TestFour(String name) {
        super(name);
    }
    
    public void testSimple() {
        
        String one = "";
        try {
            one = controller.putGet("1208p-1100a");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertEquals(one, "1.2");
        
    }

}
