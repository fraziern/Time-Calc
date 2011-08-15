package com.nickfrazier.timecalc;

import java.text.ParseException;
import java.util.Calendar;

import junit.framework.TestCase;

// UNIT TEST FOR END TO END MODEL TEST OF THIS PROJECT 
// TESTS PARSETIME & TIMECALC CLASSES

public class TestThree extends TestCase { 
    
    TimeCalc tc;
    ParseTime pt;
    Calendar testCal1 = Calendar.getInstance();
    Calendar testCal2 = Calendar.getInstance();
            
    public TestThree(String name) {
        super(name);
    }

    protected void setUp() { 

        tc = new TimeCalc(1); // round to 1 dec place
        pt = new ParseTime();

    }

    public void testUnified() {

        // Try basic calculation: 12:08pm - 11:00am
        //  which should equal 1.2.

        float testFloat = (float) 1.2;
        String testString = "1208p-1100a";

        // Step 1
        String[] testTokens = pt.tokenizeTime(testString);

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

        //              System.out.println("testCal1:" + testCal1.toString());
        //              System.out.println("testCal2:" + testCal2.toString());


        // Step 3 (getDiff)
        assertEquals(testFloat, tc.getEntry(testCal1, testCal2));


    }

    public void testBasic() {

        // Try basic calculation: 12:08pm - 11:00am

        float testFloat = (float) 1.2;
        String testString = "1208p-1100a";

        // Step 1
        String[] testTokens = pt.tokenizeTime(testString);

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

        //              System.out.println("testCal1:" + testCal1.toString());
        //              System.out.println("testCal2:" + testCal2.toString());


        // Step 3a
//        tc.setTimeout(testCal1);
//        tc.setTimein(testCal2);

        // Step 3b (getEntry)
        assertEquals(testFloat, tc.getEntry(testCal1, testCal2));

    }

    /*        public void testBreakit() {

              float tester = (float) -1;

              // Do not initialize timeout.  See what happens.

               try {
                       tc.setTimein(format.parse("11:00 AM"));
                             } catch (ParseException e) {
                                 e.printStackTrace();
                                 System.out.println("1100");
                             }

               // TODO set up the assert properly here.

               assertEquals(tester, tc.getEntry());

          } */

}
