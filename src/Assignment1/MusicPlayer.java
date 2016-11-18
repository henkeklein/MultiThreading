package Assignment1;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * Klass som skapar en JFileChooser och öpnnar en sådan.
 * Skapar även en ny tråd för att starta musiken i applikationen.
 * Har start och stop metoder för tråden som skapas. 
 * @author Henrik Klein
 *
 */
public class MusicPlayer extends JFrame implements Runnable {
	
	private Clip clip;
	private AudioInputStream ais;
	private SourceDataLine sourceDataLine;
	private AudioFormat audioFormat;
	private boolean stopPlayback = false;
	private byte tempBuffer[] = new byte[10000];
	private JFileChooser fileChooser;
	private FileNameExtensionFilter filter;
	private File file;
	private Thread play;
	private boolean song = false;
	private String soundPath;
	
	/**
	 * Konstruktor som skapar en ny JFileChooser.
	 */
	public MusicPlayer (){
		fileChooser = new JFileChooser();
	}
	/**
	 * Sätter ett filter att bara wav filer kan öppnas.
	 * Om valet är lika med vad JFileChoosern tillåter så tilldelas file, filen från JFileChooser.
	 * Hämtar sedan filen och tilldelar den till soundPath och sedan skrivs soundPath ut.
	 * Sedan anropas playAudio metoden och skickar in den filen man valt.
	 */
	public void pickFile() {
		
		filter = new FileNameExtensionFilter("wav", "wav");
		fileChooser.setFileFilter(filter);

		int returnVal = fileChooser.showOpenDialog(this);
	    
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			soundPath = file.getAbsolutePath();

	      System.out.println("["+soundPath+"]");
	    }
	 }
	/**
	 * Metod som läser in en ny fil och öppnar en ny AudioInputStream och skickar med filen.
	 * AudiFormat hämtar sedan formatet från den inlästa filen.
	 * Skapar en ny DataLine och skickar med AudiFormatet samt en SourceDataLine.
	 * Försöker sedan tilldela sourceDataLine AudioSystemets dataLineInfo.
	 * @param soundPath
	 * @throws LineUnavailableException 
	 */
	@Override
	public void run() {
		File soundFile = new File(soundPath);
		try {
			 clip = AudioSystem.getClip();
			 ais = AudioSystem.getAudioInputStream(soundFile);
			 clip.open(ais);
			 clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	      audioFormat = ais.getFormat();
	      System.out.println(audioFormat);

	      DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);

		try {
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Sätter boolean Song till true.
	 * Skapar en ny tråd och startar denna tråden.
	 */
	public void play() {
		song = true;
		play = new Thread(this);
		play.start();
	}
	/**
	 * Sätter boolean song till false.
	 * Anropar metoder i SourceDataLine för att stänga tråden.
	 * Sätter sen SourceDataLine till null.
	 */
	public void stopAudio() {
		song = false;
		clip.stop();
		clip.close();
		clip = null;
	}
	
	/**
	 * Medans tråden inte är avbruten så anropas metoden open och start från SorceDataLine.
	 * Medans AudioInputStream läser in till cnt så anropas tempBufferns längd och stopPlayback sätts till false
	 * pch song till true.
	 * Om heltalet cnt är större än noll så skriver SourceDataLine till tempBuffern med cnt.
	 * Om den går utanför while- satsen så anropas metoden stopDis.
	 */

	
}
		
		

	