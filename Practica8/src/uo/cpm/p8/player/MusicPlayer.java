package uo.cpm.p8.player;


import java.io.File;
import java.util.*;

import javazoom.jlgui.basicplayer.*;

public class MusicPlayer {
	public static final double VOLUME_MAX=100;
	public static final double VOLUME_MIN=0;
	
	private BasicPlayer basicPlayer = null;
	private List<File>listLibrary = null;
	private List<File>listPlay = null;
	private File cancionActual;  // representa la canción que está seleccionada en el ListPlay
	private int volume=0;  // representa el volumen actual
			
	public MusicPlayer(){
		basicPlayer = new BasicPlayer();
		listLibrary = new ArrayList<File>();
		listPlay = new ArrayList<File>();
		cancionActual= null;   
		volume =50;
	}
	
	
	public void addListLibrary(File[] selectedFiles) {
		listLibrary.addAll( Arrays.asList(selectedFiles));
	}
	
	public List<File> getListLibrary() {
		return listLibrary;
	}
	
	
	public List<File> getListPlay() {
		return listPlay;
	}

	
	public File getCancionActual() {
		return cancionActual;
	}

	
	public void setCancionActual(File f) {
		this.cancionActual = f;
	}
	
	
	
	/********************************************************
	 *  REPRODUCTOR
	 */
	
	public void play (){
		try {
			basicPlayer.open(cancionActual);
			basicPlayer.play();
			setVolume(volume);
		}
		catch (Exception e){	
			
		}
	}
	
	public void stop(){
		try {
			basicPlayer.stop();
		}
		catch (BasicPlayerException e){
		}
	}
	
	public void setVolume(int vol){
		try{
			this.volume = vol;
			basicPlayer.setGain(vol/VOLUME_MAX);
		}
		catch (BasicPlayerException e){
		}
	}
	
	public int getVolume() {
		return volume;
	}

	
	
	


}
