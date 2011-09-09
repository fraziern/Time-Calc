package com.nickfrazier.timecalc;

// See TimeCalc.java for general documentation

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.text.ParseException;

import javax.swing.*;

public class CalcGui {
    
    private JFrame frame;
    private JTextField field;
    private String fieldText;
    private String labelText;
    private TimeCalcControl controller = new TimeCalcControl();
    private JButton enter;
    private Font textFont;
	
	
	public static void main (String[] args) {
		CalcGui gui = new CalcGui();
		gui.go();
	}
	
// Why can't we put this in main?  Maybe try it some time.
	
	public void go() {
		
	        // Setup the font
	        textFont = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
	    
		//Gui Step 1: Make a frame
		frame = new JFrame("Time Calc");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		//Gui Step 2: Make components
		field = new JTextField(20);
	        field.setFont(textFont);
		enter = new JButton("=");
		
		field.addActionListener(new TextFieldListener());
		enter.addActionListener(new TextFieldListener());
		
		//Gui Step 3: Add component to frame
		frame.getContentPane().add(BorderLayout.NORTH, field);
	        frame.getContentPane().add(BorderLayout.EAST, enter);
		
		//Gui Step 4: Display it!
		frame.setSize(400,100);
		frame.setVisible(true);
		field.requestFocus();
		
	}

	class TextFieldListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
						
		    fieldText = field.getText();
		    try {
                      labelText = controller.putGet(fieldText);
                      // Where the magic happens.
                      field.setCaretColor(null);
                    } catch (ParseException f) {
                      labelText = fieldText;
                      field.setCaretColor(Color.RED);
                    }
		    field.setText(labelText);
		    field.requestFocus();
			
		}
	}  // close inner class
	
	
}
