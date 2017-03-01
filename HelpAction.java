import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.JOptionPane.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import jm.util.*;

public class HelpAction extends MenuAction{
	public HelpAction(ActionBuilder builder){
		super(builder);
	}

	public AbstractAction getAction(String action){
		switch(action){
			case "Tutorial":
				return Tutorial();
			default:
				return null;
		}
	}
	
	/**
	 * 
	 * */
	public AbstractAction Tutorial(){
		class tutorial extends AbstractAction{
		
			public tutorial(){
				super("Tutorial");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent e){
			
			}
		}
		
		return new tutorial();
	}	
}
