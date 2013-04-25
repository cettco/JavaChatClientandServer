import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class LoginFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	private int width= 400;
	private int height = 300;
	private int x = Toolkit.getDefaultToolkit().getScreenSize().width/3;
	private int y = Toolkit.getDefaultToolkit().getScreenSize().height/3;
	private JTextField userNameField;
	private JTextField passwordField;
	private JButton loginButton;
	private JButton registerButton;
	public LoginFrame(){
		setSize(width, height);
		setLocation(x, y);
		setLayout(null);
		initFrame();
	}
	public void initFrame(){
		JLabel l3 = new JLabel("User Name:");
		JLabel l4 = new JLabel("PassWord:");
		l3.setBounds(5, 50, 150, 30);
		add(l3);
		l4.setBounds(5,130,150,30);
		add(l4);
		
		userNameField = new JTextField();
		userNameField.setBounds(170, 50, 150, 30);
		add(userNameField);
		
		passwordField = new JTextField();
		passwordField.setBounds(170, 130, 150, 30);
		add(passwordField);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(100, 220, 100, 30);
		loginButton.addActionListener(new loginAction());
		add(loginButton);
		
		registerButton = new JButton("Register");
		registerButton.setBounds(220,220,100,30);
		registerButton.addActionListener(new registerAction());
		add(registerButton);
	}
	
	public class loginAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String serverTcpPort = null;
			String serverAddress = null;
			try {
				// parse the xml file and read the server address and server¡¯s TCP port.
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
				Document doc = dbBuilder.parse("sys.xml");
				NodeList ipList = doc.getElementsByTagName("ip");
				NodeList tcpportlist = doc.getElementsByTagName("tcpport");
				serverAddress = ipList.item(0).getTextContent();
				serverTcpPort = tcpportlist.item(0).getTextContent();
			} catch (ParserConfigurationException | SAXException | IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Can't parser the xml!");
				e1.printStackTrace();
			}
			String userName = userNameField.getText();
			String password = passwordField.getText();
			Connection connection = new Connection(userName,password, serverAddress, serverTcpPort);
			int result = connection.connect();
			if(result==101){
				Socket socket = connection.getSocket();
				int n = connection.getPort();
				ChatFrame chatFrame;
				try {
					chatFrame = new ChatFrame(userName, serverAddress, serverTcpPort,socket,n);
					new Thread(chatFrame).start();
					chatFrame.setVisible(true);
					setVisible(false);
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Can't connect to the Server!");
				}
				
			}
			else if(result==102){
				JOptionPane.showMessageDialog(null, "Your UserName or PassWord is not correct!");
			}
			else if(result==103)JOptionPane.showMessageDialog(null, "Can't connect to the Server");
		}
		
	}
	
	public class registerAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			RegisterFrame frame = new RegisterFrame();
			frame.setVisible(true);
		}
		
	}
}
