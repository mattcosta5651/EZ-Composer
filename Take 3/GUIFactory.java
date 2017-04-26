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
	//Singleton
	private static GUIFactory instance;
	
	private EZComposer composer;
	private GUI gui;
	private ActionBuilder actionBuilder;
	private JGrandStave stave;
	private Map<String, JMenu> menus;
	private Map<String, JMenuItem> menuItems;
	
	public static GUIFactory getInstance(){
		if(instance == null)
			return new GUIFactory();
		else
			return instance;
	}
	
	private GUIFactory(){}
	
	/**
	 * Builds menu bar, menus, and menu items and adds ActionListeners
	 * @return Returns the completed menu
	 * */
	
	public JMenuBar createMenuBar(EZComposer composer) throws Exception{
		actionBuilder = new ActionBuilder(composer);
		JMenuBar menuBar = new JMenuBar();
		menus = new HashMap<String, JMenu>();
		final String[] menuNames = {"File", "Edit", "View", "Composition", "Measure", "Beat", "Tools", "Help"};
		menuItems  = new HashMap<String, JMenuItem>();
		final String[][] menuItemNames = {{"New", "Open", "Save", "Save As", "Export MIDI", "Exit"},		 			   			 //File 
													{"Undo", "Redo", "Cut", "Paste", "Preferences"},	  					 //Edit
														{"Keyboard"},														 //View
															{"Time Signature", "Tempo", "Clef", "Key Signature"},			 //Composition
																{"Add", "Remove", "Clean"},									 //Measure
																	{"Tied", "Dynamic"},									 //Beat
																		{"Transpose", "Chord Assist", "Progression Assist"}, //Tools
																			{"Tutorial", "Help"}							 //Help
		};		
			try{	
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
				
				setKeyboardShortcuts(menuItems);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
			return menuBar;			
	}
	
	/**
	 * Sets basic mnemonic key shortcuts
	 * */
	private void setKeyboardShortcuts(Map<String, JMenuItem> m){
		m.get("New").setMnemonic(KeyEvent.VK_N);
		m.get("New").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		m.get("Open").setMnemonic(KeyEvent.VK_O);
		m.get("Open").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		m.get("Save").setMnemonic(KeyEvent.VK_S);
		m.get("Save").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		m.get("Preferences").setMnemonic(KeyEvent.VK_F5);
		m.get("Preferences").setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
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
		panel.setLayout(new GridLayout(3, 1));
		
		JPanel top = new JPanel();
		top.setOpaque(false);
		panel.add(top); //top padding
		
		//composition area
		stave = new JGrandStave();
		JScrollPane scroll = new JScrollPane(stave);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scroll.setViewportView(stave);
		scroll.setAutoscrolls(true);
		panel.add(scroll);
		
		JPanel bottom = new JPanel();
		bottom.setOpaque(false);
		panel.add(bottom);//bottom padding
		
		return panel;
	}
}
