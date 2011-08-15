package com.nickfrazier.timecalc;

import junit.framework.TestCase;

// UNIT TEST FOR TIMECALCCONTROL CLASS

public class TestFour extends TestCase {
    
    TimeCalcControl controller = new TimeCalcControl();
    
    public TestFour(String name) {
        super(name);
    }
    
    public void testSimple() {
        
        String one = controller.putGet("1208p-1100a");
        assertEquals(one, "1.2");
        
    }

}
