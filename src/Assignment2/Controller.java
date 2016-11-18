package Assignment2;

import java.util.LinkedList;

public class Controller {

	private CharacterBuffer bufferFile;
	private GUIMutex mutex;
	private char [] list;
	private Reader read;
	private Writer write;
	private String t;
	private String s;
	private LinkedList<Character> charList;
	private boolean bool;
	
	public Controller(GUIMutex mutex){
		this.mutex = mutex;
	}
	
	public void ReadWrite(String input, boolean bool){
		this.bool= bool;
		this.list = input.toCharArray();
		this.t = input;
		
		charList = new LinkedList<Character>();
		bufferFile = new CharacterBuffer();
		write = new Writer(bufferFile, list, bool, this);
		read = new Reader(bufferFile, bool, this);
		write.startWrite();
		read.startRead();
	}
	
	public void updateLogg(char ch){
		mutex.updateWriter("Writing char:"+ "	" + ch);
	}
	
	public String getText(){	
		for (char ch : list){
			s+= ch;
		}
		return s;
	}
	
	public void updateReader(char ch){
		if (charList.size() < t.length()){
			charList.add(new Character(ch));
			mutex.updateReader("Reading char:" + "	" + ch);
			mutex.setRecieved(getText());
			
			if (charList.size() == t.length()){
				write.stopWrite();
				read.stopRead();

			}
		}
	}


	
	
}
