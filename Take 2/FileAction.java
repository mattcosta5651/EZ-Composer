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
	
	public AbstractAction getAction(String action) throws Exception{
		switch(action){
			case "New":
				return NewFile();
			case "Open":
				return OpenFile();
			case "Save":
				return SaveFile();
			case "Save As":
				return SaveAsFile();
			case "Export MIDI":
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
	public AbstractAction NewFile() throws IOException{
		class newFile extends AbstractAction{
		
			public newFile(){
				super("New File");
				setEnabled(true);	
			}
		
			public void actionPerformed(ActionEvent action){
				boolean open = false; //check if file is open
				
				if(open){ //prompts to save, returns if cancelled
					try{
						if(savePrompt())
							System.exit(0);
						else
							return;
						}
					catch(IOException e){
						e.printStackTrace();
					}
				}
				
				//new project
				File newFile = new File("Unititled.ezmz");
				builder.getComposer().getGUI().setTitle("EZ Composer - "+newFile.getName());
				builder.getComposer().setProject(new Project(builder.getComposer())); //default constructor; not opening file
				builder.getComposer().getProject().readySong();					
			}
		}
		
		return new newFile();
	}
	
	/**
	 * Opens file chooser and loads data from selected EZ Project
	 * */
	public AbstractAction OpenFile() throws IOException{
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
					builder.getComposer().setProject(new Project(file, builder.getComposer()));
				}					
			}
		}
		
		return new openFile();
	}
	
	/**
	 * Saves changes
	 * */
	public AbstractAction SaveFile() throws IOException{
		class saveFile extends AbstractAction{
			public saveFile(){
				super("Save");
				setEnabled(true);
			}
			
			public void actionPerformed(ActionEvent action){
				if(!builder.getComposer().getProject().checkSaved()){
					if(builder.getComposer().getProject().checkFresh()){
						try{
							SaveAsFile(); //needs the save dialog
						}
						catch(IOException e){
							e.printStackTrace();
						}
					}
					else{
						//saves changes
						try{
							ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(builder.getComposer().getProject().getName()));
							out.writeObject(builder.getComposer().getProject());	
							out.close();
							builder.getComposer().getProject().save();		
						}
						catch(IOException e){
							e.printStackTrace();
						}
					}
				}				
			}
		}

		return new saveFile();
	}
	
	/**
	 * Saves changes to new file
	 * */
	public AbstractAction SaveAsFile() throws IOException{
		class saveAsFile extends AbstractAction{
			public saveAsFile(){
				super("Save As");
				setEnabled(true);
			}
			
			public void actionPerformed(ActionEvent action){
				JFileChooser saveAs = new JFileChooser();
				
				saveAs.setApproveButtonMnemonic(KeyEvent.VK_ENTER);
				int retval = saveAs.showSaveDialog(builder.getComposer().getGUI());
				
				if(retval == JFileChooser.APPROVE_OPTION){
					try{
						File newFile = new File(saveAs.getSelectedFile().getName());//get project name
						ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(newFile.getName()));
						out.writeObject(builder.getComposer().getProject());	
						out.close();
						builder.getComposer().getProject().save();
						builder.getComposer().getProject().addName(newFile.getName());
					}
					catch(IOException e){
						e.printStackTrace();
					}						
				}
			}
		}
		
		return new saveAsFile();
	}
	
	/**
	 * Exports data to audio file
	 * */
	public AbstractAction ExportFile() throws IOException{
		class exportFile extends AbstractAction{
			public exportFile(){
				super("Export MIDI");
				setEnabled(true);
			}
			
			public void actionPerformed(ActionEvent e){
				Write.midi(builder.getComposer().getProject().getPart(), builder.getComposer().getProject().getName());
			}
		}
		
		return new exportFile();
	}
	
	/**
	 * Exits the program
	 * */
	public AbstractAction Exit() throws IOException{
		class exit extends AbstractAction{
			public exit(){
				super("Exit");
				setEnabled(true);
			}
		
		
			public void actionPerformed(ActionEvent action){
				if(!builder.getComposer().getProject().checkSaved()){
					try{
						if(savePrompt())
							System.exit(0);
						else
							return;
						}
					catch(IOException e){
						e.printStackTrace();
					}
				}			
			}
		}
		return new exit();
	}
	
	/**
	 * Prompts the user to save changes with a confirm dialog
	 * @return Returns true if the user wants to continue with the parent action (e.g. New, Exit), false to cancel
	 * */
	private boolean savePrompt() throws IOException{
		try{
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
		catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}	
}
