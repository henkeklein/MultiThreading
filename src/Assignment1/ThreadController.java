package Assignment1;

import java.util.Observable;
import java.util.Observer;
/**
 * @author Henrik Klein
 * Klass som anropar GUI och anropar startmetoder för alla trådar.
 */
public class ThreadController {

	private GUIFrame frame;
	private TriangleThread triangle;
	private DisplayThread display;
	private MusicPlayer player;
	/**
	 * Konstruktor som skapar nya instanser av MusicPLayer, DisplsyThread, TriangleThread och skickar emd en frame.
	 * @param frame
	 * @param X
	 * @param Y
	 */
	public ThreadController(GUIFrame frame, int X, int Y){
		this.frame = frame;
		player = new MusicPlayer();
		display = new DisplayThread(this,X, Y);
		triangle = new TriangleThread(this);
	}
	/**
	 * Anropar metoden pickFile i klassen MusicPlayer.
	 */
	public void pickFile(){
		player.pickFile();
	}
	/**
	 * Anropar metoden playMusic i klassen MusicPlayer.
	 */
	public void playMusic(){
		player.play();
	}
	/**
	 * Anropar metoden stopMusic i klassen MusicPlayer.
	 */
	public void stopMusic(){
		player.stopAudio();
	}
	/**
	 * Anropar metoden rotImage i klassen GUIFrame.
	 */
	public void rotImage(){
		frame.rotImage();
	}
	/**
	 * Anropar metoden startRot i klassen TriangleThread.
	 */
	public void startRot(){
		triangle.startRot();
	}
	/**
	 * Anropar metoden stopRot i MusicPlayer.
	 */
	public void stopRot(){
		triangle.stopRot();
	}
	/**
	 * Anropar metoden startDis i klassen DisplayThread.
	 */
	public void startDis(){
		display.startDis();
	}
	/**
	 * Anropar metoden stopDis i klassen DisplayThread.
	 */
	public void stopDis(){
		display.stopDis();
	}
	/**
	 * Anropar metoden setPositions i klassen GUIFrame.
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y){
		frame.setPosition(x, y);
	}
	
	

	
}
