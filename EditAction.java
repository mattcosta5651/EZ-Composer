import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.JOptionPane.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import jm.util.*;

public class EditAction extends MenuAction{
	public EditAction(ActionBuilder builder){
		super(builder);
	}
	
	public AbstractAction getAction(String action){
		switch(action){
			case "Undo":
				return Undo();
			case "Redo":
				return Redo();
			case "Cut":
				return Cut();
			case "Paste":
				return Paste();
			case "Preferences":
				return Preferences();
			default:
				return null;
		}
	}	
	/**
	 * 
	 * */
	public AbstractAction Undo(){
		class undoAction extends AbstractAction{
		
			public undoAction(){
				super("Undo");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent e){
			
			}
		}
		
		return new undoAction();
	}
	
	/**
	 * 
	 * */
	public AbstractAction Redo(){
		class redoAction extends AbstractAction{
		
			public redoAction(){
				super("Redo");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent e){
			
			}
		}
		
		return new redoAction();
	}
	
	/**
	 * 
	 * */
	public AbstractAction Cut(){
		class cutAction extends AbstractAction{
		
			public cutAction(){
				super("Cut");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent e){
			
			}
		}
		
		return new cutAction();
	}
	
	/**
	 * 
	 * */
	public AbstractAction Paste(){
		class pasteAction extends AbstractAction{
		
			public pasteAction(){
				super("Paste");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent e){
			
			}
		}
		
		return new pasteAction();
	}
	
	/**
	 * 
	 * */
	public AbstractAction Preferences(){
		class preferencesAction extends AbstractAction{
		
			public preferencesAction(){
				super("Preferences");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent e){
			
			}
		}
		
		return new preferencesAction();
	}				
}
