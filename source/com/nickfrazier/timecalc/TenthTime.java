package com.nickfrazier.timecalc;

// This is the start of some work on refactoring TimeCalc.
// We're starting with a time object, that holds 3 bits of information:
//
// hrs (in 24-hour format)
// mins (0-59)
// type (ABS, REL) 
//  ABS - absolute time, like 12:30PM
//  REL - relative time, like 8.2 - aka billable hours

public class TenthTime {

  private int hrs;
  private int mins;
  private boolean type;

  private static final boolean ABS = false;
  private static final boolean REL = true;

  public TenthTime() {
    hrs = mins = 0;
    type = ABS;
  }

  public TenthTime(int hours, int minutes, boolean tType) {
    hrs = hours;
    mins = minutes;
    type = Ttype;
  }

  public putHrs(int hours) {
    hrs = hours;
  }

  public putMins(int minutes) {
    mins = minutes;
  }

  public int getHrs() {
    return hrs;
  }

  public int getMins() {
    return mins;
  }

  public boolean getType() {
    return type;
  }
}
