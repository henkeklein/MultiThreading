package Assignment1;
/**
 * Klass som implementerar interfacet Runnable och startar en tråd.
 * @author Henrik Klein
 *
 */
public class TriangleThread implements Runnable {

	private ThreadController tC;
	private Thread start;
	/**
	 * Konstruktor med en ThreadController.
	 * @param tC
	 */
	public TriangleThread (ThreadController tC){
		this.tC = tC;
	}
	/**
	 * Skapar en ny tråd och startar denna tråden.
	 */
	public void startRot(){
		start = new Thread(this);
		start.start();
	}
	/**
	 * Om stråden start är levande så avbryts tråden som körs och sätts till null.
	 */
	public void stopRot(){
		if(start.isAlive()){
			start.interrupt();
			start = null;
		}
	}
	/**
	 * Medans tråden inte är avbruten så anropas metoden rotImage från ThreadController.
	 * Sedan anropas metoden sleep med 1 sekunds mellanrum.
	 * Om den går utanför while- satsen så anropas metoden stopRot.
	 */
	@Override
	public void run() {
			try{
				while(!Thread.interrupted()){
				tC.rotImage();
				Thread.sleep(1000);
			}
				stopRot();	
			}catch (InterruptedException e){
//					e.printStackTrace();
				}
	}

}
