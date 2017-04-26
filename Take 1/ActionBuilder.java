import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.JOptionPane.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import jm.util.*;

public class ActionBuilder{
	private Map<String, MenuAction> actions;
	private EZComposer composer;
	
	public ActionBuilder(EZComposer composer){
		this.composer = composer;
		actions = new HashMap<String, MenuAction>();
		actions.put("File", new FileAction(this));
		actions.put("Edit", new EditAction(this));
		actions.put("View", new ViewAction(this));
		actions.put("Composition", new CompositionAction(this));
		actions.put("Measure", new MeasureAction(this));
		actions.put("Beat", new BeatAction(this));
		actions.put("Tools", new ToolsAction(this));
		actions.put("Help", new HelpAction(this));
	}
	
	/**
	 * Gets an action from a String
	 * @param The name of the desired action
	 * @return The appropriate Action
	 * */
	public AbstractAction getAction(String action){
		for(MenuAction menuAction : actions.values()){
			if(menuAction.getAction(action) != null)
				return menuAction.getAction(action);
		}
		return null; //never reached; action doesn't exist
	}
	
	public EZComposer getComposer(){return composer;}
}
