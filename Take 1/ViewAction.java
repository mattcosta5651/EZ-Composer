import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.JOptionPane.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import jm.util.*;

public class ViewAction extends MenuAction{
	public ViewAction(ActionBuilder builder){
		super(builder);
	}

	public AbstractAction getAction(String action){
		switch(action){
			case "Keyboard":
				return ViewKeyboard();
			default:
				return null;
		}
	}
	
	/**
	 * 
	 * */
	public AbstractAction ViewKeyboard(){
		class viewKeyboard extends AbstractAction{
		
			public viewKeyboard(){
				super("Keyboard");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent e){
			
			}
		}
		
		return new viewKeyboard();
	}		
}
