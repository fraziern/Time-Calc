package com.nickfrazier.timecalc;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

// UNIT TEST FOR LEXTIME

public class TestSeven extends TestCase {

    // Declare variables
    String in =   "  12:34a- 4:14a  ";
    String[] outArray = {"12:34a",LexTime.MINUS,"4:14a"};
    List<String> out;
    LexTime lex;
    
    public TestSeven(String name){
        super(name);
    }
    
    protected void setUp(){
        lex = new LexTime();
        out = new ArrayList<String>();
    }
    
    public void testBasic() {
        
        out = lex.lexer(in);
        for(int i=0; i < out.size(); i++) {
            assertFalse(i==3);
            assertEquals(outArray[i], out.get(i));
          }
    }

}
