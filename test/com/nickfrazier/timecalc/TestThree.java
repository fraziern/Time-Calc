package com.nickfrazier.timecalc;

import java.text.ParseException;
import java.util.Calendar;

import junit.framework.TestCase;

// INTEGRATION UNIT TEST FOR END TO END MODEL TEST OF THIS PROJECT 
// TESTS LEXTIME, PARSETIME, & TIMECALC CLASSES

// TODO: Write this before completing TimeCalc

public class TestThree extends TestCase { 
    
    TimeCalc tc;
    ParseTime pt;
    LexTime lt;
            
    public TestThree(String name) {
        super(name);
    }

    protected void setUp() { 

        tc = new TimeCalc(); 
        pt = new ParseTime();
        lt = new LexTime();

    }

    public void testUnified() {

        // Try basic calculation: 12:08pm - 11:00am
        //  which should equal 1.2.

        float testFloat = (float) 1.2;
        String testString = "1208p-1100a";

        
//        assertEquals();


    }

    /*        public void testBreakit() {

// Make sure that bad entries do not break the code
 


          } */

}
