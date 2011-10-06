package com.nickfrazier.timecalc;

import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class TimeTrainer {

// This class will have utilities for building and using a time "spellcheck" autocorrect model
// TODO: Eventually the model should be different for each logged-in user of the time calc, and will adapt
//              by adding to frequency model when the user enters times.
    
// TODO: possibly do this for relative time entries too?

    @SuppressWarnings("rawtypes")
    Map frequencies = new TreeMap();
    
    public TimeTrainer() {
        // load in corpus
        initTable();
    }
    
    // return phi(x) = standard Gaussian pdf
    public static double phi(double x) {
        return Math.exp(-x*x / 2) / Math.sqrt(2 * Math.PI);
    }

    // return phi(x, mu, signma) = Gaussian pdf with mean mu and stddev sigma
    public static double phi(double x, double mu, double sigma) {
        return phi((x - mu) / sigma) / sigma;
    }
    
    public String correct(String time) {
        return null;
    }
    
    private void loadTable() {
        // Load Hashtable from table.txt
    }
    
    private void saveTable() {
        // Save Map to table.txt
        
        try {
            FileWriter writer = new FileWriter("table.txt");
            
            Iterator iterator = frequencies.entrySet().iterator();
           
            while(iterator. hasNext()){        
              Entry entry = (Entry) iterator.next();  
              writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
            writer.close();
        } catch(IOException ex) {
            ex.printStackTrace();
            // TODO: do something non-antipattern with exception here
        }
    }
    
    private void initTable() {
        
        String ampm = new String();
        String mins = new String();
        int outputNine = 0;
        int outputFive = 0;

        for(int i=1; i<=2; i++) { // 2 cycles, AM + PM
          ampm = (i==1 ? "A" : "P");
          for(int j=1; j<=12; j++) { // 12 hours
            for(int k=0; k<=59; k++) { // 60 min
              if(k<10) { 
                mins = "0" + k;
              } else {
                mins = Integer.toString(k);
              }
              double x = 60*j+k;
              outputNine = (int)(10000*TimeTrainer.phi(x, 540, 30));
              outputFive = (int)(10000*TimeTrainer.phi(x, 300, 30));
              
              // Add all known times w/o colon, in 4 versions:
              //        both "AM/PM" and "A/P" versions,
              //        no AM or PM,
              //        and no minutes.
              this.frequencies.put(j + mins + ampm + "M", new Integer(outputNine+outputFive+1));
              this.frequencies.put(j + mins + ampm, new Integer(outputNine+outputFive+1));
              this.frequencies.put(j + mins, new Integer(outputNine+outputFive+1));
              if(mins.equals("00")) this.frequencies.put(String.valueOf(j), new Integer(outputNine+outputFive+1));
              
              // Add known times with colon, in 3 versions
              this.frequencies.put(j + ":" + mins + ampm + "M", new Integer(outputNine+outputFive+1));
              this.frequencies.put(j + ":" + mins + ampm, new Integer(outputNine+outputFive+1));
              this.frequencies.put(j + ":" + mins, new Integer(outputNine+outputFive+1));

            }
          }
        }
        
        //Add this if you want to save the table to table.txt.
        //this.saveTable();
    }
    
    public String correktor(String in) {
        
        in = in.trim().toUpperCase();
        
        //Memoization
        if(this.isKnown(in)) return in;
        
        String numerics = "0123456789AMP:";
        
        // create an arraylist of all variants of 'in' of edit distance 1.
        List<String> edits1 = new ArrayList<String>();
        String[][] splits = new String[in.length()+1][2];
        for(int i=0; i<=in.length(); i++) {
            splits[i][0] = in.substring(0,i);
            splits[i][1] = in.substring(i);
        }
        
        for(int i=0; i < splits.length; i++) {
        
            //DELETES
            if(!(splits[i][1].equals(""))) edits1.add(splits[i][0] + splits[i][1].substring(1));
            
            // TRANSPOSES
            if(splits[i][1].length() > 1) edits1.add(splits[i][0] + splits[i][1].charAt(1) + splits[i][1].charAt(0) 
                    + splits[i][1].substring(2));
            
            // REPLACES & INSERTS
            for(int j=0; j < numerics.length(); j++){
                if(!(splits[i][1].equals(""))) edits1.add(splits[i][0] + numerics.charAt(j) + splits[i][1].substring(1));
                edits1.add(splits[i][0] + numerics.charAt(j) + splits[i][1]);
            }
                        
        }
        
        // create a 'known' set
        List<String> edits = new ArrayList<String>(known(edits1));
        
        // This line for dev/debug:
        //for(String s : edits) System.out.println(s);
        
        // return the string in edits with the best score
        String bestGuess = new String(in);
        int highScore = 0;
        for(String s : edits) if((Integer) frequencies.get(s) > highScore) bestGuess = s;
            
        return bestGuess;
    }
    
    public List<String> correktor(List<String> in) {
    // This will take a token list, and correct any time entries within
    // It will ignore anything it recognizes as a floating point time
    // entry, or an operator.
    
      // Create a new list to hold corrected entries
      List<String> corrected = new ArrayList<String>();

      // Step through list
      // if period found, or if it's an operator, then just copy over
      // if abs then spellcheck
      for(String s : in) {
        if(s == "+" || s == "-" || s.matches(".*[.].*")) corrected.add(s);
        else corrected.add(this.correktor(s));

      }
    
      return corrected;

    }
    
    private List<String> known(List<String> wordset){
        List<String> out = new ArrayList<String>();
        for(String s : wordset){
            if(frequencies.containsKey(s)){
                out.add(s);
            }
        }
        return out;
    }
    
    public boolean isKnown(String s){
        return frequencies.containsKey(s);
    }
    
    public static void main(String[] args) {

        TimeTrainer tester = new TimeTrainer();
        List<String> testString = new ArrayList<String>();
        testString.add("12:94pm");
        testString.add("-");
        testString.add("76:00am");
        
        List<String> newList = new ArrayList<String>();
        newList = tester.correktor(testString);
        
        for (String s : testString) System.out.print(s);
        System.out.println("");
        for (String s : newList) System.out.print(s);
        
        
    }

}
