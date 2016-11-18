package Assignment2;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Writer implements Runnable, Observer {

	private CharacterBuffer bufferFile;
	private char[] list;
	private Thread start;
	private Random random;
	private Controller controller;
	private boolean bool;
	
	public Writer(CharacterBuffer bufferFile, char[] list, boolean bool, Controller controller){
		this.bufferFile = bufferFile;
		this.list = list;
		this.controller = controller;
		this.bool = bool;
		bufferFile.addObserver(this);
		random = new Random();
	}
	
	public void startWrite(){
		start = new Thread(this);
		start.start();
	}
	
	public void stopWrite(){
		if(start.isAlive()){
			start.interrupt();
			start = null;
		}
	}
	
	
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				for( char ch: list){
					start.sleep(random.nextInt(100) + 20);
					controller.updateLogg(ch);
					if (!bufferFile.isRead()){
						this.wait();
					}
					if (bool == true){
						synchronized(this){
							bufferFile.putOnSync(ch);
							this.wait();
						}
					} else {
						bufferFile.put(ch);
					}
				}
			}
	}catch (Exception e){
		
	}
}

	@Override
	public void update(Observable o, Object arg) {
		synchronized(this){
			notifyAll();
		}
		
	}

}
