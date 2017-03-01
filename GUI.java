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
	private Stave stave;
	
	/**
	 * Initializes the Graphical User Interface
	 * */
	public GUI(EZComposer composer){
		this.composer = composer;
		GUIFactory factory = new GUIFactory(composer, this);
		initComponents(factory);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("EZ Composer");
		
		setSize(1200, 800);	
		setVisible(true);	
	}
	
	/**
	 * Initializes the components of the frame
	 * */
	private void initComponents(GUIFactory factory){
		setJMenuBar(factory.createMenuBar());
		setContentPane(factory.createContentPane());
	}
	
	/**
	 * Sets the stave
	 * */
	private void setStave(GUIFactory factory){
		stave = factory.createStave();
	}
	
	/**
	 * Gets the current stave
	 * @return Returns the current Stave
	 * */
	public Stave getStave(){return stave;}	
}
