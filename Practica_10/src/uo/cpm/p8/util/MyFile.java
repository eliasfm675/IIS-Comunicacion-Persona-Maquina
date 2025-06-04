package uo.cpm.p8.util;

import java.io.*;

public class MyFile {
	private File f;
	
	public MyFile (File f){
		this.f = f;
	}
	
	public File getF() {
		return f;
	}

	public String toString() {
		return f.getName();
	}
}
//Otra forma



//public class MyFile extends File{
//	private static final long serialversionUID = 1L;
//	public MyFile(File parent, String child) {
//		super(parent, child);
//	}
//	public String toString() {
//		return super.getName();
//	}
//}