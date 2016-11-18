package Assignment2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class GUIMutex implements ActionListener{

	private JFrame frame;          // The Main window        
	private JLabel lblTrans;       // The transmitted text       
	private JLabel lblRec;         // The received text        
	private JRadioButton bSync;    // The sync radiobutton        
	private JRadioButton bAsync;   // The async radiobutton        
	private JTextField txtTrans;   // The input field for string to transfer    
	private JButton btnRun;        // The run button         
	private JButton btnClear;      // The clear button          
	private JPanel pnlRes;         // The colored result area       
	private JLabel lblStatus;      // The status of the transmission       
	private JTextArea listW;       // The write logger pane         
	private JTextArea listR;
	private Controller controller;
	
	
	public GUIMutex(){
	}
	
	public void Start(Controller controller) {
		this.controller = controller;
		frame = new JFrame();             
		frame.setBounds(0, 0, 601, 482); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setLayout(null); 
		frame.setTitle("Concurrent Read/Write");  
		InitializeGUI();                             // Fill in components    
		frame.setVisible(true);                
		frame.setResizable(false);                   // Prevent user from change size                 
		frame.setLocationRelativeTo(null); 			 // Start middle screen 
		
		btnRun.addActionListener(this);
		btnClear.addActionListener(this);
	}

	private void InitializeGUI(){
		// First, create the static components  59                 
		// First the 4 static texts  60                
		JLabel lab1 = new JLabel("Writer Thread Logger");                  
		lab1.setBounds(18, 29, 128, 13);               
		frame.add(lab1);           
		JLabel lab2 = new JLabel("Reader Thread Logger");         
		lab2.setBounds(388, 29, 128, 13);                 
		frame.add(lab2);           
		JLabel lab3 = new JLabel("Transmitted:");                  
		lab3.setBounds(13, 394, 100, 13);                  
		frame.add(lab3);              
		JLabel lab4 = new JLabel("Received:");                  
		lab4.setBounds(383, 394, 100, 13);              
		frame.add(lab4);

		listW = new JTextArea();  
		listW.setBounds(13, 45, 197, 342);   
		listW.setBorder(BorderFactory.createLineBorder(Color.black));     
		listW.setEditable(false);                 
		frame.add(listW);              
		listR = new JTextArea();              
		listR.setBounds(386, 45, 183, 342);              
		listR.setBorder(BorderFactory.createLineBorder(Color.black));           
		listR.setEditable(false);                 
		frame.add(listR);

		JPanel pnlTest = new JPanel();
		pnlTest.setBorder(BorderFactory.createTitledBorder("Concurrent Tester"));  
		pnlTest.setBounds(220, 45, 155, 342);  
		pnlTest.setLayout(null);  
		frame.add(pnlTest);  
		lblTrans = new JLabel("Transmitted string goes here");        // Replace with sent string 
		lblTrans.setBounds(13, 415, 200, 13);  
		frame.add(lblTrans);  
		lblRec = new JLabel("Received string goes here");             // Replace with received string  93                 
		lblRec.setBounds(383, 415, 200, 13);  
		frame.add(lblRec);

		// These are the controls on the user panel, first the radiobuttons
		bSync = new JRadioButton("Syncronous Mode", false);  
		bSync.setBounds(8, 37, 131, 17);  
		pnlTest.add(bSync);
		bAsync = new JRadioButton("Asyncronous Mode", true);
		bAsync.setBounds(8, 61, 141, 17); 
		pnlTest.add(bAsync);
		ButtonGroup grp = new ButtonGroup();
		grp.add(bSync);
		grp.add(bAsync);

		JLabel lab5 = new JLabel("String to Transfer:");
		lab5.setBounds(6, 99, 141, 13); 
		pnlTest.add(lab5);
		txtTrans = new JTextField();
		txtTrans.setBounds(6, 124, 123, 20);
		pnlTest.add(txtTrans);

		// The run button
		btnRun = new JButton("Run");
		btnRun.setBounds(26, 150, 75, 23);
		pnlTest.add(btnRun);
		JLabel lab6 = new JLabel("Running status:");
		lab6.setBounds(23, 199, 110, 13);
		pnlTest.add(lab6);

		// The colored rectangle holding result status
		pnlRes = new JPanel();
		pnlRes.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlRes.setBounds(26, 225, 75, 47);
		pnlRes.setBackground(Color.GREEN);
		pnlTest.add(pnlRes);

		// also to this text
		lblStatus = new JLabel("Staus goes here");
		lblStatus.setBounds(23, 275, 100, 13);
		pnlTest.add(lblStatus);

		// The clear input button, starts disabled
		btnClear = new JButton("Clear");
		btnClear.setBounds(26, 303, 75, 23);
		btnClear.setEnabled(true);
		pnlTest.add(btnClear);
	}
	
	public void setSent(String sent){
		lblTrans.setText(sent);
		
	}
	
	public void setRecieved(String recieved){
		lblRec.setText(recieved);
	}
	
	public void updateWriter(String write) {
		listW.append(write + "\n");
	}

	public void updateReader(String read) {
		listR.append(read + "\n");
	}
	
	public void test(boolean work){
		if(work){
			pnlRes.setBackground(Color.GREEN);
		} else {
			pnlRes.setBackground(Color.RED);
		}
		btnClear.setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRun){
			lblTrans.setText(txtTrans.getText());
			if(bSync.isSelected() == true){
				controller.ReadWrite(txtTrans.getText(), true);
			} else {
				controller.ReadWrite(txtTrans.getText(), false);
			}
			
		}
		else if(e.getSource() == btnClear){
			txtTrans.setText("");
			pnlRes.setBackground(Color.WHITE);
			listW.setText("");
			listR.setText("");
			
		}
	
	}
	
}

