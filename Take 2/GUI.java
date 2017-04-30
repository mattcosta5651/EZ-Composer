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

public class GUI extends Notate{
	private EZComposer composer;
	
	/**
	 * Initializes the Graphical User Interface
	 * */
	public GUI(EZComposer composer){
		this.composer = composer;
		setTitle("EZ Composer");
		
		setVisible(true);	
	}
	
}
