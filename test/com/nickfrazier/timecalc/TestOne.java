package com.nickfrazier.timecalc;

import java.util.Calendar;

import junit.framework.TestCase;

//UNIT TEST FOR TIMECALC CLASS

public class TestOne extends TestCase { 
    
    TimeCalc tc;
    float testFloat;
    Calendar testCal1 = Calendar.getInstance();
    Calendar testCal2 = Calendar.getInstance();
	    
	  public TestOne(String name) {
	    super(name);
	  }
	
	  protected void setUp() { 
	      
	      tc = new TimeCalc(1); // round to 1 dec place
	    
	  }
	
	  public void testBasic() {
	      
	      // Try basic calculation
	      
	      testFloat = (float) 1.2;
	      
	      testCal1.set(2010,12,4,12,8);  // 12:08 PM
//	      tc.setTimeout(testCal1);
	      testCal2.set(2010,12,4,11,0);  // 11:00 AM
//	      tc.setTimein(testCal2);
	      assertEquals(testFloat, tc.getEntry(testCal1, testCal2));
	      
	      testFloat = (float) 8;
	      
	      testCal1.set(2010,12,4,5,0);  // 5:00 PM? or AM? if last, then should assume PM
	      testCal2.set(2010,12,4,9,0);  // 9:00 AM
	      assertEquals(testFloat, tc.getEntry(testCal1, testCal2));
	       
	  }
	  
/*	  public void testBreakit() {
	      
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
