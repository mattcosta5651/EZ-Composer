/*
 * EZComposer.java
 * 
 * <mattcosta8723@localhost>
 * 
 * 
 */

import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.JOptionPane.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class EZComposer implements ActionListener{
	private GUI gui;
	private Project project;
	
	public static void main (String args[]) {
		new EZComposer();
	}
	
	/**
	 * Calls for a new Graphical User Interface and initializes new project
	 * */
	public EZComposer(){
		gui = new GUI(this);
		newFile();
	}
	
	/**
	 * Sets basic mnemonic key shortcuts
	 * */
	public void setKeyboardShortcuts(Map<String, JMenuItem> m){
		m.get("New").setMnemonic(KeyEvent.VK_N);
		m.get("New").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		m.get("Open").setMnemonic(KeyEvent.VK_O);
		m.get("Open").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		m.get("Save").setMnemonic(KeyEvent.VK_S);
		m.get("Save").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		m.get("Preferences").setMnemonic(KeyEvent.VK_F5);
		m.get("Preferences").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
	}
	
	/**
	 * Implementation of actionPerformed
	 * */
	@Override
	public void actionPerformed(ActionEvent e){
System.out.println("debug");
		String action = e.getActionCommand();
System.out.println(action);
		
		//New projects
		if(action.equals("New"))
			newFile();
		//Opening projects
		else if(action.equals("Open"))
			openFile();
		else if(action.equals("Save"))
			saveFile();
		else if(action.equals("Save As"))
			saveAsFile();
		else if(action.equals("Export"))
			exportFile();
		else if(action.equals("Exit"))	
			exit();
	}
	
	//Action Events
	/**
	 * Creates a new EZ Composer project; prompts user if changes are not saved
	 * */
	private void newFile(){
		boolean open = false; //check if file is open
		
		if(open){ //prompts to save, returns if cancelled
			if(!savePrompt()){
				return;
			}
		}
		
		//new project
		File newFile = new File("Unititled.ezmz");
		gui.setTitle("EZ Composer - "+newFile.getName());
		project = new Project(); //default constructor; not opening file
	}
	
	/**
	 * Opens file chooser and loads data from selected EZ Project
	 * */
	private void openFile(){
		//File chooser setup
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setApproveButtonMnemonic(KeyEvent.VK_ENTER);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("EZ Project", "ezmz"));
		
		//Get file
		int retval = fileChooser.showOpenDialog(gui);
		
		if(retval == JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			//Load project
			project = new Project(file);
		}	
	}
	
	/**
	 * Saves changes
	 * */
	private void saveFile(){
		if(!project.checkSaved()){
			if(project.checkFresh())
				saveAsFile(); //needs the save dialog
			else{
				//saves changes
				project.save();
			}
		}
	}
	
	/**
	 * Saves changes to new file
	 * */
	private void saveAsFile(){
		JFileChooser saveAs = new JFileChooser();
		
		saveAs.setApproveButtonMnemonic(KeyEvent.VK_ENTER);
		int retval = saveAs.showSaveDialog(gui);
		
		if(retval == JFileChooser.APPROVE_OPTION){
			File newFile = new File("blah");//get project name
		}
		
		project.save();
	}
	
	/**
	 * Exports data to audio file
	 * */
	private void exportFile(){
		
	}
	
	/**
	 * Exits the program
	 * */
	private void exit(){
		if(!project.checkSaved()){
			if(savePrompt()){
				//close program
			}
			else
				return;
		}
	}
	
	/**
	 * Prompts the user to save changes with a confirm dialog
	 * @return Returns true if the user wants to continue with the parent action (e.g. New, Exit), false to cancel
	 * */
	private boolean savePrompt(){
		int retval = JOptionPane.showConfirmDialog(gui, 
			"Would you like to save?", 
			"Unsaved changes", 
			JOptionPane.YES_NO_CANCEL_OPTION);
		
		if(retval == JOptionPane.YES_OPTION) //Saves changes
			saveAsFile();
		else if(retval == JOptionPane.CANCEL_OPTION) //Returns, doing nothing
			return false;	
		
		return true;
	}
}
