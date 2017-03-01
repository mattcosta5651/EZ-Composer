import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.JOptionPane.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import jm.util.*;

public class CompositionAction extends MenuAction{
	public CompositionAction(ActionBuilder builder){
		super(builder);
	}

	public AbstractAction getAction(String action){
		switch(action){
			case "Time Signature":
				return TimeSig();
			case "Tempo":
				return Tempo();
			case "Clef":
				return Clef();
			case "Key Signature":
				return KeySig();
			default:
				return null;
		}
	}
	
	/**
	 * 
	 * */
	public AbstractAction TimeSig(){
		class timeSig extends AbstractAction{
		
			public timeSig(){
				super("Time Signature");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent e){
			
			}
		}
		
		return new timeSig();
	}
	
	/**
	 * 
	 * */
	public AbstractAction Tempo(){
		class tempo extends AbstractAction{
		
			public tempo(){
				super("Tempo");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent e){
			
			}
		}
		
		return new tempo();
	}	

	/**
	 * 
	 * */
	public AbstractAction Clef(){
		class clef extends AbstractAction{
		
			public clef(){
				super("Clef");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent e){
			
			}
		}
		
		return new clef();
	}
	
	/**
	 * 
	 * */
	public AbstractAction KeySig(){
		class keySig extends AbstractAction{
		
			public keySig(){
				super("Key Signature");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent e){
			
			}
		}
		
		return new keySig();
	}			
}
