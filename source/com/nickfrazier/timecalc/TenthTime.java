package com.nickfrazier.timecalc;

// This is the start of some work on refactoring TimeCalc.
// We're starting with a time object, that holds 3 pieces of information:
//
// hrs (in 24-hour format)
// mins (0-59)
// type (ABS, REL) 
//  ABS - flag signaling that other methods should treat like absolute time, like 12:30PM
//  REL - flag signaling that other methods treat like relative time, like 8.2 - 
//        aka billable hours
//  

// TODO: methods below


public class TenthTime {

  private int hrs;
  private int mins;
  private boolean type;

  static final boolean ABS = false;
  static final boolean REL = true;

  public TenthTime() {
    hrs = mins = 0;
    type = ABS;
  }

  public TenthTime(int hours, int minutes, boolean tType) {
    
    hrs = hours;
    mins = minutes;
    type = tType;
  }

  public void setHrs(int hours) {
      // TODO boundary checking

      hrs = hours;
  }

  public void setMins(int minutes) {
      // TODO boundary checking

      mins = minutes;
  }
  
  public void setType(boolean typeIn) {
      type = typeIn;
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
  
  public float toFloat() {  // Returns tenths
    return (float)this.hrs + roundUp((float)this.mins/60, 1);
  }
  
  public String toFloatString() {
    return Float.toString(this.toFloat());      
  }

//  public String toClockString() {
//  }

//  public TenthTime plus(TenthTime first) {
//      
//  }
  
  public TenthTime minus(TenthTime smaller) {
    
    int finalmins = 0, finalhrs = 0;  // output is always rel.
      
    // if we have an abs - abs (most common) and its something like 5:23 - 9:22, assume the first number is PM.
    if((this.type==ABS) && (smaller.getType() == ABS) && this.isLessThan(smaller)) hrs+=12;  

    finalmins = this.getTimeInMins() - smaller.getTimeInMins();
    finalhrs = finalmins / 60;
    finalmins = finalmins % 60;        

    return new TenthTime(finalhrs, finalmins, REL);
   
 }
  
//  public boolean isGreaterThan(TenthTime first) {
//      
//  }
  
  public boolean isLessThan(TenthTime first) {
      return(this.getTimeInMins() < first.getTimeInMins());
  }
  
  private float roundUp(float num, int place) {
      
      // This is a standard rounding algorithm, using ceil to round up.
      
      float p = (float) Math.pow(10, place); 
      num *= p;
      num = (float) Math.ceil((double) num);
      return (float) num/p;
      
  }
  
  private int getTimeInMins() {
      return mins+(hrs*60);
  }
}
