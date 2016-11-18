package Assignment1;

import java.io.*;
import java.util.*;

public class DisplayThread implements Runnable{

	private int x;
	private int y;
	private Random random;
	private ThreadController tC;
	private Thread start;
	
	public DisplayThread(ThreadController tC, int x, int y){
		this.tC = tC;
		this.x = x;
		this.y = y;
		random = new Random();
	}
	/**
	 * Skapar en ny tråd och startar denna tråden.
	 */
	public void startDis(){
		start = new Thread(this);
		start.start();
	}
	/**
	 * Om stråden start är levande så avbryts tråden som körs och sätts till null.
	 */
	public void stopDis(){
		if(start.isAlive()){
			start.interrupt();
			start= null;
		}
	}
	/**
	 * Medans tråden inte är avbruten så anropas metoden setPostions från ThreadController och klassen random
	 * Med nya koordinater för setPostions metoden.
	 * Sedan anropas metoden sleep med 1 milli sekunds mellanrum.
	 * Om den går utanför while- satsen så anropas metoden stopDis.
	 */
	@Override
	public void run() {
		try{
				while(!Thread.interrupted()){
				tC.setPosition(random.nextInt(x), random.nextInt(y));
				Thread.sleep(100);
				}
				stopDis();
			}catch (InterruptedException e){
//				e.printStackTrace();
			}
		}
}
