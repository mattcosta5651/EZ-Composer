import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.JOptionPane.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import jm.util.*;

public class MeasureAction extends MenuAction{
	public MeasureAction(ActionBuilder builder){
		super(builder);
	}
	
	public AbstractAction getAction(String action){
		switch(action){
			case "Add":
				return Add();
			case "Remove":
				return Remove();
			case "Clean":
				return Clean();
			default:
				return null;
		}
	}
		
	/**
	 * 
	 * */
	public AbstractAction Add(){
		class addMeasure extends AbstractAction{
		
			public addMeasure(){
				super("Add");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent e){
			
			}
		}
		
		return new addMeasure();
	}
	
	/**
	 * 
	 * */
	public AbstractAction Remove(){
		class removeMeasure extends AbstractAction{
		
			public removeMeasure(){
				super("Remove");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent e){
			
			}
		}
		
		return new removeMeasure();
	}
	
	/**
	 * 
	 * */
	public AbstractAction Clean(){
		class cleanMeasure extends AbstractAction{
		
			public cleanMeasure(){
				super("Clean");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent e){
			
			}
		}
		
		return new cleanMeasure();
	}				
}
