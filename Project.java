import java.io.*;
import jm.music.data.*;

public class Project{
	private boolean fresh;
	private boolean saved;
	
	private File file;
	private Score score;
	private Part part;
	private Phrase song;
	private Note[] notes;
	
	
	
	public Project(){
		fresh = true;  //default new projects haven't saved new file
		saved = false; //default new projects are unsaved
		part = new Part(jm.DEFAULT_INSTRUMENT);
	}
	
	public Project(Project other){
		file = other.getFile();
		song = other.getSong();
	}
	
	public Project(File f){
		this();
		save();
	}
	
	public void save(){
		saved = true;
		fresh = false;
	}
	public boolean checkSaved(){return saved;}
	public boolean checkFresh(){return fresh;}
	
	public File getFile(){return file;}
	public Phrase getSong(){return song;}
}
