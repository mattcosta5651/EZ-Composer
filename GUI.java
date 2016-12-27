import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.JOptionPane.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GUI extends JFrame{
	private EZComposer composer;
	/**
	 * Initializes the Graphical User Interface
	 * */
	public GUI(EZComposer composer){
		this.composer = composer;
		initComponents();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("EZ Composer");
		
		setSize(1200, 800);	
		setVisible(true);	
	}
	
	/**
	 * Initializes the components of the frame
	 * */
	private void initComponents(){
		setJMenuBar(buildMenu());
		setContentPane(buildContentPane());
	}
	
	/**
	 * Builds menu bar, menus, and menu items and adds ActionListeners
	 * @return Returns the completed menu
	 * */
	private JMenuBar buildMenu(){
	JMenuBar menuBar = new JMenuBar();
	Map<String, JMenu> menus = new HashMap<String, JMenu>();;
	final String[] menuNames = {"File", "Edit", "View", "Composition", "Measure", "Beat", "Tools", "Help"};
	Map<String, JMenuItem> menuItems = new HashMap<String, JMenuItem>();;
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
				menuItems.put(item, new JMenuItem(item)); //maps menu item
				menuItems.get(item).setActionCommand(item); //sets ActionCommand to menu item name
				menuItems.get(item).addActionListener(composer); //adds ActionListener to menu item
				menus.get(name).add(item); //adds menu item to appropriate menu
			}
			menuBar.add(menus.get(name));
			counter++;
		}
		
		composer.setKeyboardShortcuts(menuItems);
		
		return menuBar;	
	}
	
	/**
	 * Builds the content pane and lays out components
	 * @return Returns the completed panel as a content pane
	 * */
	private JPanel buildContentPane(){
		JPanel panel = new JPanel();		
		panel.setBackground(Color.blue);
		
		
		return panel;
	}
}
