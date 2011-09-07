package com.nickfrazier.timecalc;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(TestTwo.class);
        suite.addTestSuite(TestSix.class);      // TenthTime
        suite.addTestSuite(TestThree.class);
        suite.addTestSuite(TestFour.class);
        suite.addTestSuite(TestSeven.class);    // LexTime

        //$JUnit-END$
        return suite;
    }

}
