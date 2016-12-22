import javax.swing.*;
import java.util.*;


public class GUI extends JFrame{
	private JPanel panel;
	
	private JMenuBar menuBar;
	private Map<String, JMenu> menus;
	private final String[] menuNames = {"File", "Edit", "View", "Composition", "Tools", "Help"};
	private Map<String, JComponent> menuItems;
	private final String[][] menuItemNames = {{"New", "Open", "Save", "Save As", "Import", "Export"}, 
												{"Undo", "Redo", "Cut", "Paste", "Preferences"},
													{"Keyboard"},
														{"Transpose"},
															{""},
																{"Tutorial"}
	};
	
	public static void main(String[] args){
		new GUI().setVisible(true);
	}
	
	public GUI(){
		initComponents();
	}
	
	private void initComponents(){
		//setLookAndFeel(null);
		
		panel = new JPanel();
		
		menuBar = new JMenuBar();
		menus = new HashMap<String, JMenu>();
		menuItems = new HashMap<String, JComponent>();
		
		int counter = 0;
		for(String name : menuNames){
			menus.put(name, new JMenu(name));
			for(String item : menuItemNames[counter]){
				menuItems.put(item, new JMenuItem(item));
				menus.get(name).add(item);
			}
			menuBar.add(menus.get(name));
			counter++;
		}
		menus.get("File").add(new JMenu("Recent"));
		panel.add(menuBar);
		add(panel);
	}
}
