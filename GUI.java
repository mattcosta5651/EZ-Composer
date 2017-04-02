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

public class GUI extends JFrame implements JMC{
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
		setJMenuBar(GUIFactory.getInstance().createMenuBar(composer));
		setContentPane(GUIFactory.getInstance().createContentPane());
	}
	
	/**
	 * Gets the current stave
	 * @return Returns the current Notate
	 * */
	public Notate getNotate(){return GUIFactory.getInstance().getNotate();}	
}
