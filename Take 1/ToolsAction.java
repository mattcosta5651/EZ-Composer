import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.JOptionPane.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import jm.util.*;

public class ToolsAction extends MenuAction{
	public ToolsAction(ActionBuilder builder){
		super(builder);
	}

	public AbstractAction getAction(String action){
		switch(action){
			case "Transpose":
				return Transpose();
			case "Chord Assist":
				return ChordAssist();
			case "Progression Assist":
				return ProgressionAssist();
			default:
				return null;
		}
	}
	
	/**
	 * 
	 * */
	public AbstractAction Transpose(){
		class transpose extends AbstractAction{
		
			public transpose(){
				super("Transpose");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent e){
			
			}
		}
		
		return new transpose();
	}
	
	/**
	 * 
	 * */
	public AbstractAction ChordAssist(){
		class chordAssist extends AbstractAction{
		
			public chordAssist(){
				super("Chord Assist");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent e){
			
			}
		}
		
		return new chordAssist();
	}	
	
	/**
	 * 
	 * */
	public AbstractAction ProgressionAssist(){
		class progressionAssist extends AbstractAction{
		
			public progressionAssist(){
				super("Progression Assist");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent e){
			
			}
		}
		
		return new progressionAssist();
	}				
}
