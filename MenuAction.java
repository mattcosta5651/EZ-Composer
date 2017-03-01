import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.JOptionPane.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import jm.util.*;

public abstract class MenuAction{
	protected ActionBuilder builder;

	public MenuAction(ActionBuilder builder){
		this.builder = builder;
	}
	
	public abstract AbstractAction getAction(String action);
}
