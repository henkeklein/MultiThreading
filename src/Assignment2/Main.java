package Assignment2;

public class Main {

	public static void main (String[] args){
			GUIMutex mutex = new GUIMutex();
			Controller controller = new Controller(mutex);
			mutex.Start(controller);
		}
}
