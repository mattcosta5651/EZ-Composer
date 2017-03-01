import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.JOptionPane.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import jm.util.*;

public class FileAction extends MenuAction{
	public FileAction(ActionBuilder builder){
		super(builder);
	}
	
	public AbstractAction getAction(String action){
		switch(action){
			case "New":
				return NewFile();
			case "Open":
				return OpenFile();
			case "Save":
				return SaveFile();
			case "Save As":
				return SaveAsFile();
			case "Export":
				return ExportFile();
			case "Exit":
				return Exit();
			default:
				return null;					
		}
	}
	/**
	 * Creates a new EZ Composer project; prompts user if changes are not saved
	 * */
	public AbstractAction NewFile(){
		class newFile extends AbstractAction{
		
			public newFile(){
				super("New File");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent e){
				boolean open = false; //check if file is open
				
				if(open){ //prompts to save, returns if cancelled
					if(!savePrompt()){
						return;
					}
				}
				
				//new project
				File newFile = new File("Unititled.ezmz");
				builder.getComposer().getGUI().setTitle("EZ Composer - "+newFile.getName());
				builder.getComposer().setProject(new Project()); //default constructor; not opening file
				builder.getComposer().getProject().readySong();					
			}
		}
		
		return new newFile();
	}
	
	/**
	 * Opens file chooser and loads data from selected EZ Project
	 * */
	public AbstractAction OpenFile(){
		class openFile extends AbstractAction{
			public openFile(){
				super("Open File");
				setEnabled(true);
			}
			
			public void actionPerformed(ActionEvent e){
				//File chooser setup
				JFileChooser fileChooser = new JFileChooser();
				
				fileChooser.setApproveButtonMnemonic(KeyEvent.VK_ENTER);
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setMultiSelectionEnabled(false);
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("EZ Project", "ezmz"));
				
				//Get file
				int retval = fileChooser.showOpenDialog(builder.getComposer().getGUI());
				
				if(retval == JFileChooser.APPROVE_OPTION){
					File file = fileChooser.getSelectedFile();
					//Load project
					builder.getComposer().setProject(new Project(file));
				}					
			}
		}
		
		return new openFile();
	}
	
	/**
	 * Saves changes
	 * */
	public AbstractAction SaveFile(){
		class saveFile extends AbstractAction{
			public saveFile(){
				super("Save");
				setEnabled(true);
			}
			
			public void actionPerformed(ActionEvent e){
				if(!builder.getComposer().getProject().checkSaved()){
					if(builder.getComposer().getProject().checkFresh())
						SaveAsFile(); //needs the save dialog
					else{
						//saves changes
						builder.getComposer().getProject().save();
					}
				}				
			}
		}

		return new saveFile();
	}
	
	/**
	 * Saves changes to new file
	 * */
	public AbstractAction SaveAsFile(){
		class saveAsFile extends AbstractAction{
			public saveAsFile(){
				super("Save As");
				setEnabled(true);
			}
			
			public void actionPerformed(ActionEvent e){
				JFileChooser saveAs = new JFileChooser();
				
				saveAs.setApproveButtonMnemonic(KeyEvent.VK_ENTER);
				int retval = saveAs.showSaveDialog(builder.getComposer().getGUI());
				
				if(retval == JFileChooser.APPROVE_OPTION){
					File newFile = new File("blah");//get project name
				}
				
				builder.getComposer().getProject().save();				
			}
		}
		
		return new saveAsFile();
	}
	
	/**
	 * Exports data to audio file
	 * */
	public AbstractAction ExportFile(){
		class exportFile extends AbstractAction{
			public exportFile(){
				super("Export");
				setEnabled(true);
			}
			
			public void actionPerformed(ActionEvent e){
				//export here
			}
		}
		
		return new exportFile();
	}
	
	/**
	 * Exits the program
	 * */
	public AbstractAction Exit(){
		class exit extends AbstractAction{
			public exit(){
				super("Exit");
				setEnabled(true);
			}
		
		
			public void actionPerformed(ActionEvent e){
				if(!builder.getComposer().getProject().checkSaved()){
					if(savePrompt())
						System.exit(0);
					else
						return;
				}			
			}
		}
		return new exit();
	}
	
	/**
	 * Prompts the user to save changes with a confirm dialog
	 * @return Returns true if the user wants to continue with the parent action (e.g. New, Exit), false to cancel
	 * */
	private boolean savePrompt(){
		int retval = JOptionPane.showConfirmDialog(builder.getComposer().getGUI(), 
			"Would you like to save?", 
			"Unsaved changes", 
			JOptionPane.YES_NO_CANCEL_OPTION);
		
		if(retval == JOptionPane.YES_OPTION) //Saves changes
			SaveAsFile();
		else if(retval == JOptionPane.CANCEL_OPTION) //Returns, doing nothing
			return false;	
		
		return true;
	}	
}
