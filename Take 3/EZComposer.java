/*
 * EZComposer.java
 * 
 * Created by Matthew Costa
 * Implements jMusic 
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
import jm.util.*;
import jm.gui.cpn.*;

public class EZComposer{ 
	private GUI gui;
	private ActionBuilder actionBuilder;
	private Project project;
	
	public static void main (String args[]) {
		new EZComposer();
	}
	
	/**
	 * Calls for a new Graphical User Interface and initializes new project
	 * */
	public EZComposer(){
		project = new Project(this);
		gui = new GUI(this);
	
		actionBuilder = new ActionBuilder(this);
		actionBuilder.getAction("New");	
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
	 * Gets the current project
	 * @return Returns the current project
	 * */
	public Project getProject(){return project;}
	
	/**
	 * Sets the project to a new object
	 * @param The project to be set
	 * */
	public void setProject(Project p){project = p;}

	/**
	 * Gets the current GUI
	 * @return Returns the current GUI
	 * */
	 public GUI getGUI(){return gui;}
}
