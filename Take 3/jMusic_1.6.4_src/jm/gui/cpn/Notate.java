/*

<This Java Class is part of the jMusic API version 1.5, March 2004.>

Copyright (C) 2000 Andrew Brown and Andrew Sorensen

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or any
later version.

This program is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.

*/ 

package jm.gui.cpn; 

import java.awt.event.*;
import java.awt.*; 
import java.lang.Integer;
import java.io.*;
import javax.sound.midi.*;
import jm.gui.cpn.JmMidiPlayer;
import jm.music.data.*;
import jm.midi.*;
import jm.util.Read;
import jm.util.Write;
import jm.gui.cpn.Stave;
import jm.util.Play;
import jm.JMC;
import javax.swing.*;

/**
* This class displays a frame with a common practice notation display
* of the score passed to it.
* The parameter and add data by text attributes only work on the first stave.
* Some GPL changes for jMusic CPN Written by Al Christians 
* (achrist@easystreet.com).
* Contributed by Trillium Resources Corporation, Oregon's
* leading provider of unvarnished software.
* @author Andrew Brown
*/


public class Notate  extends JPanel
{
  private int scrollHeight = 130; 
  private int locationX = 0; 
  private int locationY = 0;
  
  private String lastFileName = "*.mid";
  private String lastDirectory = "";
  private String fileNameFilter = "*.mid";
  

  private Phrase beforeZoom = new Phrase();
  private Phrase afterZoom = new Phrase();
  
  private int height = 0;
  private int width = 700;
  private Score score;
  private Phrase[] phraseArray;
  
  public Notate() { this(new Phrase());
    clearZoom();
  }
  
  public Notate(int paramInt1, int paramInt2) {
    this(new Phrase(), paramInt1, paramInt2);
    clearZoom();
  }
  
  public Notate(Phrase paramPhrase) {
    this(paramPhrase, 0, 0);
    clearZoom();
  }
  
  private void clearZoom() {
    zoomed = false;
  }
  
  public Notate(Phrase paramPhrase, int paramInt1, int paramInt2)
  {
    clearZoom();
    score = new Score(new Part(paramPhrase));
    locationX = paramInt1;
    locationY = paramInt2;
    score = new Score(new Part(paramPhrase));
    init();
  }
  
  public Notate(Score paramScore, int paramInt1, int paramInt2)
  {
    clearZoom();
    score = paramScore;
    locationX = paramInt1;
    locationY = paramInt2;
    init();
  }
  
  private Stave[] staveArray;
  
  private Dialog keyDialog;
  private Dialog timeDialog;
  
 private MenuItem keySig;
  
  private MenuItem open;  

  private MenuItem openJmXml;
  
  private MenuItem openjm;
    private MenuItem play;
 
  private MenuItem stop;
  
  private MenuItem delete;
  
  private MenuItem clear;
  
  private MenuItem newStave;
  
  private MenuItem close;
  
  private MenuItem timeSig;
  
  private MenuItem saveJmXml;  

  private MenuItem saveJM;
  
  private MenuItem saveMidi;
  
  private MenuItem quit;
    private MenuItem trebleStave;
 
  private MenuItem bassStave;
  
  private MenuItem pianoStave;
 
  public void init()
  {
    setupArrays();
    makeGrandStave();
    
    calcHeight();
  }
  
  private void setupArrays()
  {
    phraseArray = new Phrase[score.size()];
    staveArray = new Stave[score.size()];
    
    for (int i = 0; i < staveArray.length; i++) {
      phraseArray[i] = score.getPart(i).getPhrase(0);
      staveArray[i] = new PianoStave();
      staveArray[i].setKeySignature(score.getKeySignature());
      staveArray[i].setMetre(score.getNumerator());
      staveArray[i].setBarNumbers(true);
    }
  }
  
  private void setupConstraints() {
    constraints.weightx = 100.0D;
    constraints.weighty = 0.0D;
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    
    constraints.fill = 1;
  }
  
  private void calcHeight()
  {
    height = 0;
    for (int i = 0; i < staveArray.length; i++) {
      height += staveArray[i].getSize().height;
    }
  }
  
  private void makeAppropriateStaves()
  {
    Stave[] arrayOfStave = new Stave[staveArray.length];
    for (int i = 0; i < score.size(); i++) {
      Phrase localPhrase = score.getPart(i).getPhrase(0);
      arrayOfStave[i] = new PianoStave();
      if ((localPhrase.getHighestPitch() < 93) && (localPhrase.getLowestPitch() > 54)) {
        arrayOfStave[i] = new TrebleStave();
      } else if ((localPhrase.getHighestPitch() < 65) && (localPhrase.getLowestPitch() > 35)) {
        arrayOfStave[i] = new BassStave();
      } else if ((localPhrase.getHighestPitch() > 93) || (localPhrase.getLowestPitch() < 35))
        arrayOfStave[i] = new GrandStave();
    }
    updateAllStaves(arrayOfStave);
  }
  
  private void makeTrebleStave()
  {
    Stave[] arrayOfStave = new Stave[score.size()];
    for (int i = 0; i < staveArray.length; i++) {
      arrayOfStave[i] = new TrebleStave();
    }
    updateAllStaves(arrayOfStave);
  }
  
  private void updateAllStaves(Stave[] paramArrayOfStave) {
    int i = 0;
    int j = 0;
    int k = 0;
    
    removeAll();
    for (int m = 0; m < staveArray.length; m++)
    {
      paramArrayOfStave[m].setKeySignature(staveArray[m].getKeySignature());
      paramArrayOfStave[m].setMetre(staveArray[m].getMetre());
      paramArrayOfStave[m].setBarNumbers(staveArray[m].getBarNumbers());
      paramArrayOfStave[m].setPhrase(phraseArray[m]);
      
      staveArray[m] = paramArrayOfStave[m];
      paramArrayOfStave[m] = null;
      
      add(staveArray[m]);
      i += j;
      k += staveArray[m].getPanelHeight();
    }
    calcHeight();
  }
  
  private void makeBassStave()
  {
    Stave[] arrayOfStave = new Stave[score.size()];
    for (int i = 0; i < staveArray.length; i++) {
      arrayOfStave[i] = new BassStave();
    }
    updateAllStaves(arrayOfStave);
  }
 
  private void makePianoStave()
  {
    Stave[] arrayOfStave = new Stave[score.size()];
    for (int i = 0; i < arrayOfStave.length; i++) {
      arrayOfStave[i] = new PianoStave();
    }
    updateAllStaves(arrayOfStave);
  }
  
  private void makeGrandStave()
  {
    Stave[] arrayOfStave = new Stave[score.size()];
    for (int i = 0; i < arrayOfStave.length; i++) {
      arrayOfStave[i] = new GrandStave();
    }
    updateAllStaves(arrayOfStave);
  }
 

  class PlayRepeater
    extends Thread
  {
    JmMidiPlayer midiPlayer;
    
    Notate n;
    
    public PlayRepeater(String paramString, Notate paramNotate, JmMidiPlayer paramJmMidiPlayer)
    {
      super();
      n = paramNotate;
      midiPlayer = paramJmMidiPlayer;
    }
    
    public void run() {
      do {
        midiPlayer.play();
      } while (!n.timeToStop);
    }
  }

  private MenuItem grandStave;
 
  private MenuItem automaticStave;
  private MenuItem appendMidiFile;
   private MenuItem insertMidiFile;
    private MenuItem setParameters;
  
  private MenuItem playAll;
    private MenuItem playMeasure;
  
  private MenuItem repeatAll;
  
  private MenuItem repeatMeasure;
  
  private MenuItem stopPlay;
  
  private MenuItem earTrain;
  
  private MenuItem addNotes;
  
  private MenuItem adjustTiming;
  
  private MenuItem viewDetails;
  
  private MenuItem viewTitle;
  
  private MenuItem viewZoom;
  
  private MenuItem barNumbers;
  
  public boolean timeToStop;
  
  private Panel scoreBG;
  
  private GridBagConstraints constraints;
  
  private GridBagLayout layout;
 
  private ScrollPane scroll;
  
  private boolean zoomed;
  
  public void openMidi(Component paramComponent)
  {
    Score localScore = new Score();
    JFileChooser localJFileChooser = new JFileChooser();
    int i = localJFileChooser.showOpenDialog(paramComponent);
    String str = localJFileChooser.getName(localJFileChooser.getSelectedFile());
    if ((str != null) && (i == 0)) {
      lastFileName = str;
      lastDirectory = localJFileChooser.getCurrentDirectory().getName();
      Read.midi(localScore, lastDirectory + str);
      setNewScore(localScore);
    }
  }
  
  private void setNewScore(Score paramScore)
  {
    score = paramScore;
    
    setupArrays();
    makeAppropriateStaves();
  }
  



  public void openJM(Component paramComponent)
  {
    JFileChooser localJFileChooser = new JFileChooser();
    int i = localJFileChooser.showOpenDialog(paramComponent);
    String str = localJFileChooser.getName(localJFileChooser.getSelectedFile());
    
    if ((str != null) && (i == 0)) {
      Score localScore = new Score();
      lastDirectory = localJFileChooser.getCurrentDirectory().getName();
      Read.jm(localScore, lastDirectory + localJFileChooser.getSelectedFile().getName());
      setNewScore(localScore);
    }
  }
  



  public void openJMXML(Component paramComponent)
  {
    JFileChooser localJFileChooser = new JFileChooser();
    int i = localJFileChooser.showOpenDialog(paramComponent);
    String str = localJFileChooser.getName(localJFileChooser.getSelectedFile());
    if ((str != null) && (i == 0)) {
      Score localScore = new Score();
      lastDirectory = localJFileChooser.getCurrentDirectory().getName();
      Read.xml(localScore, lastDirectory + str);
      setNewScore(localScore);
    }
  }
  




  public void saveMidi(Component paramComponent)
  {
    JFileChooser localJFileChooser = new JFileChooser();
    int i = localJFileChooser.showSaveDialog(paramComponent);
    

    if (i == 0) {
      Write.midi(score, localJFileChooser.getSelectedFile().getName());
    }
  }
  
  public Score getScore()
  {
    return score;
  }
  

  public void saveJM(Component paramComponent)
  {
    JFileChooser localJFileChooser = new JFileChooser();
    int i = localJFileChooser.showSaveDialog(paramComponent);
    

    if (i == 0) {
      Write.jm(score, localJFileChooser.getCurrentDirectory().getName() + localJFileChooser.getSelectedFile().getName());
    }
  }
  


  public void saveJMXML(Component paramComponent)
  {
    JFileChooser localJFileChooser = new JFileChooser();
    int i = localJFileChooser.showSaveDialog(paramComponent);
    

    if (i == 0) {
      Write.xml(score, localJFileChooser.getCurrentDirectory().getName() + localJFileChooser.getSelectedFile().getName());
    }
  }
  



  public Phrase readMidiPhrase(Component paramComponent)
  {
    JFileChooser localJFileChooser = new JFileChooser();
    int i = localJFileChooser.showOpenDialog(paramComponent);
    String str = localJFileChooser.getSelectedFile().getName();
    Phrase localPhrase = new Phrase(0.0D);
    Score localScore = new Score();
    if ((str != null) && (i == 0)) {
      Read.midi(localScore, localJFileChooser.getCurrentDirectory().getName() + str);
    }
    localScore.clean();
    if ((localScore.size() > 0) && (localScore.getPart(0).size() > 0)) { localPhrase = localScore.getPart(0).getPhrase(0);
    }
    return localPhrase;
  }
  
  private Score getLastMeasure() {
    double d1 = phraseArray[0].getNumerator();
    double d2 = score.getEndTime();
    int i = (int)(d2 / d1);
    double d3 = d1 * i;
    if (d3 == d2) d3 -= d1;
    Score localScore = score.copy(d3, d2);
    
    for (int j = 0; j < localScore.size(); j++) {
      localScore.getPart(j).getPhrase(0).setStartTime(0.0D);
    }
    return localScore;
  }
  


  private static double getRhythmAdjustment(double paramDouble1, double paramDouble2)
  {
    double d1 = paramDouble1 / paramDouble2;
    
    double d2 = 1.0E-5D;
    
    double d3 = 0.0D;
    
    double d4 = Math.floor(d1);
    
    while ((Math.floor(d1 + d2) > d4) && (d2 > 1.0E-14D)) {
      d3 = d2;
      d2 /= 2.0D;
    }
    return d3 * paramDouble2;
  }
  
  private static void adjustTimeValues(Phrase paramPhrase) {
    double d1;
    double d2;
    for (int i = 0; i < paramPhrase.size(); i++) {
      d1 = paramPhrase.getNote(i).getDuration();
      d2 = getRhythmAdjustment(d1, 0.00390625D);
      paramPhrase.getNote(i).setDuration(d1 + d2);
    }
    
    double d3 = 0.0D;
    for (int i = 0; i < paramPhrase.size(); i++) {
      d1 = paramPhrase.getNote(i).getDuration();
      d3 += d1;
      d2 = getRhythmAdjustment(d3, 1.0D);
      paramPhrase.getNote(i).setDuration(d1 + d2);
      d3 += d2;
    }
  }
  


  public void toggleDisplayTitle()
  {
    for (int i = 0; i < staveArray.length; i++) {
      staveArray[i].setDisplayTitle(!staveArray[i].getDisplayTitle());
    }
  }
}
