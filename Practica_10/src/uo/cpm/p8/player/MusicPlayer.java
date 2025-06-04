package uo.cpm.p8.player;


import java.io.File;
import java.util.*;

import javazoom.jlgui.basicplayer.*;
import uo.cpm.p8.util.MyFile;

public class MusicPlayer {
	public static final double VOLUME_MAX=100;
	public static final double VOLUME_MIN=0;
	
	private BasicPlayer basicPlayer = null;
	private List<MyFile>listLibrary = null;
	private List<MyFile>listPlay = null;
	private MyFile cancionActual;  // representa la canci�n que est� seleccionada en el ListPlay
	private int volume=0;  // representa el volumen actual
			
	public MusicPlayer(){
		basicPlayer = new BasicPlayer();
		listLibrary = new ArrayList<MyFile>();
		listPlay = new ArrayList<MyFile>();
		cancionActual= null;   
		volume =50;
	}
	
	
	public void addListLibrary(File[] selectedFiles) {
		MyFile[] a = new MyFile[selectedFiles.length];
		for(int i=0; i <selectedFiles.length; i++) {
			a[i] = new MyFile(selectedFiles[i]);
		}
		listLibrary.addAll( Arrays.asList(a));
		
	}
	public void addListPLay(List<MyFile> selectedValueList) {
		//listPlay.addAll( Arrays.asList(selectedFiles)); permite canciones repetidas
		for(int i=0; i<selectedValueList.size(); i++) {
			
			if(!listPlay.contains(selectedValueList.get(i))) { //si la cancion NO esta en la playList se mete
				listPlay.add(selectedValueList.get(i));
			}
			
			//actualizar la cancion actual
			if(cancionActual==null) {
				cancionActual = listPlay.get(0); // si no hay ninguna seleccionada cogemos la primera de de la lista
			}
		}
	}
	public int getCancionIndice(MyFile cancion) {
//		if(listPlay.get(0).equals(cancion) || listPlay.get(listPlay.size()-1).equals(cancion)) {
//			return 0;
//		}
		for(int i=0; i<listPlay.size(); i++) {
			if(listPlay.get(i).equals(cancion)) {
				return i;
			}
		}
		return -1;
	}
	public void siguienteCancion(MyFile cancion) {
		int posicion = getCancionIndice(cancion);
		if(posicion>=listPlay.size()-1) {
			setCancionActual(listPlay.get(0));
		}else {
			setCancionActual(listPlay.get(posicion+1));
		}
	}
	public void previaCancion(MyFile cancion) {
		int posicion = getCancionIndice(cancion);
		if(posicion<=0) {
			setCancionActual(listPlay.get(0));
		}else {
			setCancionActual(listPlay.get(posicion-1));
		}
	
	}
	private void borrarCancionPlayList(MyFile cancion) {
		for(MyFile a: listPlay) {
			if(a.equals(cancion)) {
				if(a.equals(getCancionActual())) {
					stop();
				}
				listPlay.remove(a);
				return;
			}
		}
	}
	public void borrarCancionPlayListVarias(List<MyFile> list) {
		for(int i=0; i<list.size(); i++) {
			borrarCancionPlayList(list.get(i));
		}
	}
	private void borrarCancionLibrary(MyFile cancion) {
		for(MyFile a: listLibrary) {
			if(a.equals(cancion)) {
				if(a.equals(getCancionActual())) {
					stop();
				}
				getCancionIndice(a);
				listLibrary.remove(a);
				borrarCancionPlayList(a);
				return;
			}
		}
	}
	public void borrarCancionLibraryVarias(List<MyFile> list) {
		for(int i=0; i<list.size(); i++) {
			borrarCancionLibrary(list.get(i));
		}
	}
	
	public List<MyFile> getListLibrary() {
		return listLibrary;
	}
	
	
	public List<MyFile> getListPlay() {
		return listPlay;
	}

	
	public MyFile getCancionActual() {
		return cancionActual;
	}

	
	public void setCancionActual(MyFile f) {
		this.cancionActual = f;
	}
	
	
	
	/********************************************************
	 *  REPRODUCTOR
	 */
	
	public void play (){
		try {
			basicPlayer.open(cancionActual.getF());
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
