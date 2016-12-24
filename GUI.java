import javax.swing.*;
import javax.swing.filechooser.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GUI extends JFrame implements ActionListener{
	public static void main(String[] args){
		new GUI();
	}
	
	/**
	 * Initializes the Graphical User Interface
	 * */
	public GUI(){
		initComponents();
	}
	
	/**
	 * Initializes the components of the frame
	 * */
	private void initComponents(){
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("EZ Composer");
		
		setSize(1200, 800);
		setJMenuBar(buildMenu());
		setContentPane(buildContentPane());
		setVisible(true);
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
	final String[][] menuItemNames = {{"New", "Open", "Save", "Save As", "Import", "Export", "Exit"},		 			 //File 
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
				menuItems.get(item).addActionListener(this); //adds ActionListener to menu item
				menus.get(name).add(item); //adds menu item to appropriate menu
			}
			menuBar.add(menus.get(name));
			counter++;
		}
		
		setKeyboardShortcuts(menuItems);
		
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
	
	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println("debug");
		String action = e.getActionCommand();
		System.out.println(action);
		
		//Opening projects
		if(action.equals("Open")){
			//File chooser setup
			JFileChooser fileChooser = new JFileChooser();
			
			fileChooser.setApproveButtonMnemonic(KeyEvent.VK_ENTER);
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setMultiSelectionEnabled(false);
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("EZ Project", "ezmz"));
			
			
			//Get file
			int retval = fileChooser.showOpenDialog(this);
			
			if(retval == JFileChooser.APPROVE_OPTION){
				File project = fileChooser.getSelectedFile();
				//Open project
			}
			
			
		}
	}
}
