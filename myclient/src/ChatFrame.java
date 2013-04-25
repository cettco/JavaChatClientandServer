import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class ChatFrame extends JFrame implements Runnable{
	private static final long serialVersionUID = 1L;
	private String userName;
	private int x = Toolkit.getDefaultToolkit().getScreenSize().width/4;
	private int y = Toolkit.getDefaultToolkit().getScreenSize().height/4;
	private int width = 600;
	private int height = 500;
	private JTextArea msgtArea;
	private JTextArea inputArea;
	private Socket socket;
	private DatagramSocket udpSocket;
	private Sender sender;
	private Receiver receiver;
	public ChatFrame(String userName,String serverAddress,String serverTcpPort,Socket socket,int udpPort) throws SocketException{
		this.userName = userName;
		
		setSize(width,height);
		setLocation(x, y);
		setLayout(null);
		
		init();
		try{
		this.socket = socket;
		udpSocket = new DatagramSocket();
		sender = new Sender(udpSocket,udpPort,serverAddress);
		
		receiver = new Receiver(this.socket);
		}catch(Exception e){
			
		}
	}
	public void init(){
		msgtArea = new JTextArea();
		JScrollPane j1 = new JScrollPane(msgtArea);
		j1.setBounds(5, 5, 400, 300);
		add(j1);
		
		inputArea = new JTextArea();
		JScrollPane j2 = new JScrollPane(inputArea);
		j2.setBounds(5, 310, 400, 100);
		add(j2);
		
		JButton sendbButton = new JButton("Send");
		JButton quitButton = new JButton("Quit");
		sendbButton.setBounds(300,430,100,30);
		sendbButton.addActionListener(new sendAction());
		add(sendbButton);
		quitButton.setBounds(410,430,100,30);
		quitButton.addActionListener(new quitAction());
		add(quitButton);
	}
	public class sendAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("send start");
			String msg = userName+">"+inputArea.getText()+"\n";
			sender.send(msg);
			inputArea.setText("");
		}
		
	}
	//client quit
	public class quitAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			//System.out.println("send start");
			String msg = "@quit"+userName+"\n";
			sender.send(msg);
			System.exit(0);
			try {
				socket.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true){
				String msg = receiver.receive();
				msgtArea.append(msg);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
