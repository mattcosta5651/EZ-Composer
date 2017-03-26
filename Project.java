import java.io.*;
import java.util.*;
import jm.music.data.*;

public class Project{
	private boolean fresh;
	private boolean saved;
	
	private File file;
	private Score score;
	private Part part;
	private Phrase phrase;
	private Note[] notes;
	
	public Project(){
		fresh = true;  //default new projects haven't saved new file
		saved = false; //default new projects are unsaved
		part = new Part(Part.DEFAULT_INSTRUMENT);
		phrase = new Phrase(0.0);
		notes = new Note[3];
		/*notes[0] = new Note(Note.A); 
		notes[1] = new Note(Note.C); 
		notes[2] = new Note(Note.E);*/
		readySong();
	}
	
	public Project(Project other){
		file = other.getFile();
		part = other.getPart();
	}
	
	public Project(File f){
		this();
		save();
	}
	
	public void save(){
		saved = true;
		fresh = false;
	}
	
	public void readySong(){
		//phrase.addNoteList(notes);
		part.addPhrase(phrase);
	}
	
	public boolean checkSaved(){return saved;}
	public boolean checkFresh(){return fresh;}
	
	public File getFile(){return file;}
	public Part getPart(){return part;}
	public Phrase getPhrase(){return phrase;}
}
