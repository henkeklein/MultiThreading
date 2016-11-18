
package Assignment1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The GUI for assignment 1
 */
public class GUIFrame implements ActionListener
{
	/**
	 * These are the components you need to handle.
	 * You have to add listeners and/or code
	 */
	private JFrame frame;		// The Main window
	private JButton btnOpen;	// Open sound file button
	private JButton btnPlay;	// Play selected file button
	private JButton btnStop;	// Stop music button
	private JButton btnDisplay;	// Start thread moving display
	private JButton btnDStop;	// Stop moving display thread
	private JButton btnTriangle;// Start moving graphics thread
	private JButton btnTStop;	// Stop moving graphics thread
	private JLabel lblPlaying;	// Hidden, shown after start of music
	private JLabel lblPlayURL;	// The sound file path
	private JPanel pnlMove;		// The panel to move display in
	private JPanel pnlRotate;	// The panel to move graphics in
	private Rotate lblRot;
	private JLabel lblBounce;

	private MusicPlayer player;
	private ThreadController tC;

	/**
	 * Constructor
	 */
	public GUIFrame()
	{
		
//		player = new MusicPlayer(tC);
//		controller = new ThreadController();
//		display = new DisplayThread();
//		display.addObserver(controller);
		Start();
	}
	
	/**
	 * Starts the application
	 */
	public void Start()
	{
		frame = new JFrame();
		frame.setBounds(0, 0, 494, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Multiple Thread Demonstrator");
		readRotImage();
		InitializeGUI();					// Fill in components
		frame.setVisible(true);
		frame.setResizable(false);			// Prevent user from change size
		frame.setLocationRelativeTo(null);	// Start middle screen
		
		btnOpen.addActionListener(this);
		btnPlay.addActionListener(this);
		btnStop.addActionListener(this);
		btnTriangle.addActionListener(this);
		btnTStop.addActionListener(this);
		btnDisplay.addActionListener(this);
		btnDStop.addActionListener(this);
	}
	
	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI() {
			
		// The play panel
		JPanel pnlSound = new JPanel();
		Border b1 = BorderFactory.createTitledBorder("Music Player");
		pnlSound.setBorder(b1);
		pnlSound.setBounds(12, 12, 450, 100);
		pnlSound.setLayout(null);
		
		// Add the buttons and labels to this panel
		btnOpen = new JButton("Open");
		btnOpen.setBounds(6, 71, 75, 23);
		pnlSound.add(btnOpen);
		
		btnPlay = new JButton("Play");
		btnPlay.setBounds(88, 71, 75, 23);
		pnlSound.add(btnPlay);
		
		btnStop = new JButton("Stop");
		btnStop.setBounds(169, 71, 75, 23);
		pnlSound.add(btnStop);
		
		lblPlaying = new JLabel("Now Playing...",JLabel.CENTER);
		lblPlaying.setFont(new Font("Serif", Font.BOLD, 20));
		lblPlaying.setBounds(128, 16, 120, 30);
		pnlSound.add(lblPlaying);
		
		lblPlayURL = new JLabel("Music url goes here");
		lblPlayURL.setBounds(10, 44, 115, 13);
		pnlSound.add(lblPlayURL);
		// Then add this to main window
		frame.add(pnlSound);
		
		// The moving display outer panel
		JPanel pnlDisplay = new JPanel();
		Border b2 = BorderFactory.createTitledBorder("Display Thread");
		pnlDisplay.setBorder(b2);
		pnlDisplay.setBounds(12, 118, 222, 269);
		pnlDisplay.setLayout(null);
		
		// Add buttons and drawing panel to this panel
		btnDisplay = new JButton("Start Display");
		btnDisplay.setBounds(10, 226, 121, 23);
		pnlDisplay.add(btnDisplay);
		
		btnDStop = new JButton("Stop");
		btnDStop.setBounds(135, 226, 75, 23);
		pnlDisplay.add(btnDStop);
		
		pnlMove = new JPanel();
		pnlMove.setBounds(10,  19,  200,  200);
		Border b21 = BorderFactory.createLineBorder(Color.black);
		lblBounce = new JLabel("Jumping around");
		pnlMove.add(lblBounce);
		pnlMove.setBorder(b21);
		pnlDisplay.add(pnlMove);
		
		// Then add this to main window
		frame.add(pnlDisplay);
		
		// The moving graphics outer panel
		JPanel pnlTriangle = new JPanel();
		Border b3 = BorderFactory.createTitledBorder("Triangle Thread");
		pnlTriangle.setBorder(b3);
		pnlTriangle.setBounds(240, 118, 222, 269);
		pnlTriangle.setLayout(null);
		
		// Add buttons and drawing panel to this panel
		btnTriangle = new JButton("Start Rotate");
		btnTriangle.setBounds(10, 226, 121, 23);
		pnlTriangle.add(btnTriangle);
		
		btnTStop = new JButton("Stop");
		btnTStop.setBounds(135, 226, 75, 23);
		pnlTriangle.add(btnTStop);
		
		pnlRotate = new JPanel();
		pnlRotate.setBounds(10,  19,  200,  200);
		Border b31 = BorderFactory.createLineBorder(Color.black);
		pnlRotate.add(lblRot);
		pnlRotate.setBorder(b31);
		pnlTriangle.add(pnlRotate);
		// Add this to main window
		frame.add(pnlTriangle);
		
		tC = new ThreadController(this, pnlRotate.getWidth(), pnlRotate.getHeight());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnOpen){
				tC.pickFile();
			}
			else if (e.getSource() == btnPlay){
					tC.playMusic();
			}
			else if(e.getSource() == btnStop){
				tC.stopMusic();
			}
			else if(e.getSource() == btnDisplay){
				tC.startDis();
			}
			else if(e.getSource() == btnDStop){
				tC.stopDis();
			}
			else if (e.getSource() == btnTriangle){
				tC.startRot();
			}
			else if (e.getSource() == btnTStop){
				tC.stopRot();
			}
			
				
	}
	public void setPosition(int x, int y){
		lblBounce.setLocation(x, y);
	}
	
	public void rotImage(){
		pnlRotate.repaint();
	}
	
	public void readRotImage (){
		try{
			lblRot = new Rotate(ImageIO.read(new File("src/world.png")));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private class Display extends JPanel{
		private BufferedImage buffered;
	
		
		public Display(BufferedImage buffered){
			super();
		
			this.buffered = buffered;
		}
	}
	
	private class Rotate extends JPanel{
		private BufferedImage buffered;
		private int rot;
		
		public Rotate(BufferedImage buffered){
			super();
			rot = 50;
			this.buffered = buffered;
		}
		@Override
		public Dimension getPreferredSize(){
			return new Dimension(buffered.getHeight(), buffered.getWidth());
		}
		@Override
		public void paintComponent(Graphics graph){
			super.paintComponent(graph);
			
			Graphics2D graph2D = (Graphics2D) graph;
			
			graph2D.rotate((rot+=50), buffered.getHeight() / 4, buffered.getWidth() / 4);
			graph2D.drawImage(buffered, 0, 0, null);
		}
		
	}
}
