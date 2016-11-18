package Assignment2;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class CharacterBuffer extends Observable {
	
	private char ch;
	private boolean read;
	
	public CharacterBuffer(){
	}
	
	public void put (char object){
		this.ch = object;
	}
	
	public char get(){
		return ch;
	}
	
	public boolean isRead(){
		return read = true;
	}
	
	public void putOnSync (char object){
		this.ch = object;
		read = false;
		setChanged();
		notifyObservers();
	}
	
	public char getOnSync(){
		read = true;
		setChanged();
		notifyObservers();
		return ch;
	}
	
}
