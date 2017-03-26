import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.JOptionPane.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import jm.util.*;
import jm.gui.helper.*;

public class HelpAction extends MenuAction{
	public HelpAction(ActionBuilder builder){
		super(builder);
	}

	public AbstractAction getAction(String action){
		switch(action){
			case "Tutorial":
				return Tutorial();
			case "Help":
				return Help();
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
	
	public AbstractAction Help(){
		class help extends AbstractAction{
			public help(){
				super("Help");
				setEnabled(true);
			}
			
			public void actionPerformed(ActionEvent e){
				HelperGUI hg =  new HelperGUI();
				hg.setVisible(true);				
			}
		}
		
		return new help();
	}	
}
