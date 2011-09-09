package com.nickfrazier.timecalc;

import junit.framework.TestCase;

//UNIT TEST FOR TENTHTIME CLASS

public class TestSix extends TestCase { 
    
// declare variables
    TenthTime tt1;
    TenthTime tt2;
    
	  public TestSix(String name) {
	    super(name);
	  }
	
	  protected void setUp() { 
	      
	      tt1 = new TenthTime();
	      tt2 = new TenthTime(5, 24, TenthTime.ABS);
	    
	  }
	
	  public void testBasic() {
	      
	      // Try the following methods:
	      // 1) Getting and setting
	      tt1.setHrs(9);
	      tt1.setMins(1);
	      tt1.setType(TenthTime.ABS);
	      assertEquals(9, tt1.getHrs());
	      assertEquals(1, tt1.getMins());
	      assertEquals(TenthTime.ABS, tt1.getType());
	      
	      // 2) Less than
	      assertFalse(tt1.isLessThan(tt2));
	      
	      // 3) Subtraction
	      TenthTime tt3 = new TenthTime();
	      tt3 = tt2.minus(tt1);
	      assertEquals(8, tt3.getHrs());
	      assertEquals(23, tt3.getMins());
	      
	      // 4) ToFloat
	      float ans = (float) 8.4;
	      assertEquals(ans, tt3.toFloat());
	      
	      // 5) ToFloatString
	      String stAns = "8.4";
	      assertEquals(stAns, tt3.toFloatString());
	  } 
	      
	      

	    
	}
