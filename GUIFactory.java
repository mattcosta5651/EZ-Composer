import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.JOptionPane.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import jm.gui.cpn.*;
import jm.gui.sketch.*;
import jm.JMC;
import jm.music.data.*;
import jm.util.*;
import jm.gui.show.*;

public class GUIFactory{
	private EZComposer composer;
	private GUI gui;
	private ActionBuilder actionBuilder;
	private Map<String, JMenu> menus;
	private Map<String, JMenuItem> menuItems;
	
	
	public GUIFactory(EZComposer composer, GUI gui){
		this.composer = composer;
		this.gui = gui;
		actionBuilder = new ActionBuilder(composer);
	}
	
	/**
	 * Builds menu bar, menus, and menu items and adds ActionListeners
	 * @return Returns the completed menu
	 * */
	
	public JMenuBar createMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		menus = new HashMap<String, JMenu>();
		final String[] menuNames = {"File", "Edit", "View", "Composition", "Measure", "Beat", "Tools", "Help"};
		menuItems  = new HashMap<String, JMenuItem>();
		final String[][] menuItemNames = {{"New", "Open", "Save", "Save As", "Export", "Exit"},		 			   			 //File 
													{"Undo", "Redo", "Cut", "Paste", "Preferences"},	  					 //Edit
														{"Keyboard"},														 //View
															{"Time Signature", "Tempo", "Clef", "Key Signature"},			 //Composition
																{"Add", "Remove", "Clean"},									 //Measure
																	{"Tied", "Dynamic"},									 //Beat
																		{"Transpose", "Chord Assist", "Progression Assist"}, //Tools
																			{"Tutorial"}									 //Help
		};		
			
			int counter = 0;
			for(String name : menuNames){
				menus.put(name, new JMenu(name));
				for(String item : menuItemNames[counter]){
					menuItems.put(item, new JMenuItem(actionBuilder.getAction(item))); //maps menu item
					menus.get(name).add(menuItems.get(item)); //adds menu item to appropriate menu
				}
				menuBar.add(menus.get(name));
				counter++;
			}
			
			composer.setKeyboardShortcuts(menuItems);
			
			return menuBar;			
	}
	
	public Map<String, JMenu> getMenus(){return menus;}
	public Map<String, JMenuItem> getMenuItems(){return menuItems;}
	
		/**
	 * Builds the content pane and lays out components
	 * @return Returns the completed panel as a content pane
	 * */
	public JPanel createContentPane(){
		JPanel panel = new JPanel();		
		panel.setBackground(new Color(72, 140, 250));
		panel.setOpaque(true);
		panel.setLayout(new GridLayout(5, 1));
		
		JPanel top = new JPanel();
		top.setOpaque(false);
		panel.add(top); //top padding
		
		panel.add(createStave()); //clefs and score
		panel.add(createStave());
		panel.add(new JPanel()); //piano
		
		JPanel bottom = new JPanel();
		bottom.setOpaque(false);
		panel.add(bottom);//bottom padding
		
		return panel;
	}
	
	/**
	 * Builds the stave containing music
	 * @return Returns the completed stave
	 * */
	public Stave createStave(){
		Stave stave = new PianoStave(composer.getProject().getPhrase());
		return stave;
	}
}
