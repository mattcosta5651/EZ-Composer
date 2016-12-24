import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener, MouseListener{
	private JPanel panel;
	
	//global menu variables
	private JMenuBar menuBar;
	private Map<String, JMenu> menus;
	private final String[] menuNames = {"File", "Edit", "View", "Composition", "Measure", "Beat", "Tools", "Help"};
	private Map<String, JMenuItem> menuItems;
	private final String[][] menuItemNames = {{"New", "Open", "Save", "Save As", "Import", "Export", "Exit"},			 //File 
												{"Undo", "Redo", "Cut", "Paste", "Preferences"},	  					 //Edit
													{"Keyboard"},														 //View
														{"Time Signature", "Tempo", "Clef", "Key Signature"},			 //Composition
															{"Add", "Remove", "Clean"},									 //Measure
																{"Tied", "Dynamic"},									 //Beat
																	{"Transpose", "Chord Assist", "Progression Assist"}, //Tools
																		{"Tutorial"}									 //Help
	};
	
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
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(1600, 1800));
		panel.setBackground(Color.blue);
		panel.add(buildMenu());
		add(panel);
		setVisible(true);
	}
	
	/**
	 * Builds menu bar, menus, and menu items and adds ActionListeners
	 * @return Returns the completed menu
	 * */
	private JMenuBar buildMenu(){
		menuBar = new JMenuBar();
		menus = new HashMap<String, JMenu>();
		menuItems = new HashMap<String, JMenuItem>();
		
		int counter = 0;
		for(String name : menuNames){
			menus.put(name, new JMenu(name));
			for(String item : menuItemNames[counter]){
				menuItems.put(item, new JMenuItem(item)); //maps menu item
				menuItems.get(item).addActionListener(this); //adds ActionListener to menu item
				menuItems.get(item).setActionCommand(item); //sets ActionCommand to menu item name
				menuItems.get(item).addMouseListener(this);
				menus.get(name).add(item); //adds menu item to appropriate menu
			}
			menuBar.add(menus.get(name));
			counter++;
		}
		
		setKeyboardShortcuts();
		
		
		return menuBar;	
	}
	
	/**
	 * Sets basic mnemonic key shortcuts
	 * */
	private void setKeyboardShortcuts(){
		menuItems.get("New").setMnemonic(KeyEvent.VK_N);
		menuItems.get("Open").setMnemonic(KeyEvent.VK_O);
		menuItems.get("Save").setMnemonic(KeyEvent.VK_S);
		menuItems.get("Preferences").setMnemonic(KeyEvent.VK_F5);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println("debug");
		String action = e.getActionCommand();
		System.out.println(action);
	}
	
	@Override	
	public void mouseExited(MouseEvent e){
		
	}
	
	@Override	
	public void mouseEntered(MouseEvent e){
		
	}
	
	@Override	
	public void mouseReleased(MouseEvent e){
		
	}
	
	@Override	
	public void mousePressed(MouseEvent e){
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e){
		System.out.println("Debug");	
	}
}
