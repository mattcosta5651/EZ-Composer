import java.io.*;
import java.util.*;
import jm.music.data.*;

public class Project implements Serializable{
	private boolean fresh;
	private boolean saved;
	private String name;
	
	private EZComposer composer;
	private Part part;
	private Phrase phrase;
	
	public Project(EZComposer composer){
		fresh = true;  //default new projects haven't saved new file
		saved = false; //default new projects are unsaved
		this.composer = composer;
		readySong();
	}
	
	public Project(Project other){
		part = other.getPart();
		name = other.getName();
		composer = other.getComposer();
	}
	
	public Project(File f, EZComposer composer){
		this(composer);
		save();
	}
	
	public void save(){
		saved = true;
		fresh = false;
	}
	
	public EZComposer getComposer(){return composer;}
	
	public void readySong(){
		//part = composer.getNotate().getScore().getPart(0);
	}
	
	public void addName(String n){name = n;} 
	
	public boolean checkSaved(){return saved;}
	public boolean checkFresh(){return fresh;}
	public String getName(){return name;}
	public Part getPart(){return part;}
}
