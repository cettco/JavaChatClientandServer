import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Login extends JFrame{

	private static final long serialVersionUID = 1L;

	private int width = 400;
	private int heigth = 300;
	private int x = Toolkit.getDefaultToolkit().getScreenSize().width/3;
	private int y = Toolkit.getDefaultToolkit().getScreenSize().height/3;
	private JTextField udpPortField;
	private JTextField tcpPortField;
	public Login(){
		setSize(width,heigth);
		setLocation(x, y);
		setLayout(null);
		init();
	}
	public void init(){
		JLabel l1 = new JLabel("UDP Port:");
		l1.setBounds(5, 30, 100, 30);
		add(l1);
		JLabel l2 = new JLabel("TCP Port");
		l2.setBounds(5,100,100,30);
		add(l2);
		udpPortField = new JTextField();
		udpPortField.setBounds(150, 30, 100, 30);
		add(udpPortField);
		
		tcpPortField = new JTextField();
		tcpPortField.setBounds(150,100,100,30);
		add(tcpPortField);
		
		JButton startbButton = new JButton("Start");
		startbButton.setBounds(150,150,100,30);
		startbButton.addActionListener(new startAction());
		add(startbButton);
	}
	public class startAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String udpPort = udpPortField.getText();
			String tcpPort = tcpPortField.getText();
			setVisible(false);
			RunFrame runFrame;
			try {
				runFrame = new RunFrame(tcpPort, udpPort);
				runFrame.setVisible(true);	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
		
	}
}
