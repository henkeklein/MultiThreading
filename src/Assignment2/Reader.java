package Assignment2;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Reader implements Runnable, Observer {
	
	private Thread start;
	private boolean bool;
	private CharacterBuffer bufferFile;
	private Controller controller;
	private Random random;
	
	public Reader(CharacterBuffer bufferFile, boolean bool, Controller controller){
		this.bufferFile = bufferFile;
		this.bool = bool;
		this.controller = controller;
		bufferFile.addObserver(this);
		random = new Random();
	}
	
	public void startRead(){
		start = new Thread(this);
		start.start();
	}
	
	public void stopRead(){
		if(start.isAlive()){
			start.interrupt();
			start = null;
		}
	}
	
	public boolean work(){
		return bool;
	}
	
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				start.sleep(random.nextInt(100) + 20);
				if (bool == true){
					synchronized(this){
						char ch = bufferFile.getOnSync();
						if (ch != '\0'){
							controller.updateReader(ch);
						}
//						this.wait();
						if (bufferFile.isRead()){
							this.wait();
						}
								
					}
				} else {
					char ch = bufferFile.get();
					if (ch != '\0');
					controller.updateReader(ch);
				}
			}
		} catch (Exception e){
			
		}
		start = null;
		
	}

	@Override
	public void update(Observable o, Object arg) {
		synchronized(this){
			notifyAll();
		}
		
	}

}
