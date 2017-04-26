import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.JOptionPane.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import jm.util.*;

public class BeatAction extends MenuAction{
	public BeatAction(ActionBuilder builder){
		super(builder);
	}

	public AbstractAction getAction(String action){
		switch(action){
			case "Tied":
				return Tied();
			case "Dynamic":
				return Dynamic();
			default:
				return null;
		}
	}
	
	/**
	 * 
	 * */
	public AbstractAction Tied(){
		class tiedNote extends AbstractAction{
		
			public tiedNote(){
				super("Tied");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent e){
			
			}
		}
		
		return new tiedNote();
	}
	
	/**
	 * 
	 * */
	public AbstractAction Dynamic(){
		class dynamic extends AbstractAction{
		
			public dynamic(){
				super("Dynamic");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent e){
			
			}
		}
		
		return new dynamic();
	}				
}
