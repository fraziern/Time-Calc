package com.nickfrazier.timecalc;

import java.text.ParseException;

import junit.framework.TestCase;

// INTEGRATION UNIT TEST FOR END TO END MODEL TEST OF THIS PROJECT 
// TESTS LEXTIME, PARSETIME, & TIMECALC CLASSES

public class TestThree extends TestCase { 
    
    TimeCalc tc;
            
    public TestThree(String name) {
        super(name);
    }

    protected void setUp() { 

        tc = new TimeCalc(); 

    }

    public void testUnified() {

        // Try basic calculation: 12:08pm - 11:00am
        //  which should equal 1.2.

        String testFloat = "6.2";
        String testString = "508p-1100a";
        try {
            assertEquals(testFloat, tc.process(testString));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        testFloat = "7.5";
        testString = "5-9-.5";
        try {
            assertEquals(testFloat, tc.process(testString));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
                
    }

    public void testBreakit() {

    // Make sure that bad entries do not break the code
    
        String badLexerString = "3447730";
        
        try {
            tc.process(badLexerString);
            fail("Should have thrown a ParseException");
        } catch (ParseException e) { }
        
        String badLexerString2 = "";
        
        try {
            tc.process(badLexerString2);
            fail("Should have thrown an Exception.");
        } catch (ParseException e) { }
        
        String badParserString = "3443-125";
        
        try {
            tc.process(badParserString);
            fail("Should have thrown an Exception.");
        } catch (ParseException e) { }

    } 

}
